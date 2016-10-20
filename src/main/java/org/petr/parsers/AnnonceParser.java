package org.petr.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.petr.proxy.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;

/**
 * Created by petr on 13.9.16.
 */

@Component(value = "full")
public class AnnonceParser implements IParser {

    public List<Contact> parseTab(String url) throws Exception {
        //url = "http://www.annonce.cz/hledam-praci-chci-vydelat$18.html?department=&zps=&location_country=&q=&jobsSourceRejected=1&maxAge=&nabidkovy=0&action=Hledej&page=1";
        Document doc = Jsoup.connect(url).get();
        String title = doc.title();
        ArrayList<Contact> contactList = new ArrayList<>();

        if (title.equals("Zamezení přístupu na server annonce.cz | Annonce.cz")) {
            System.out.println("Stopped by CAPTCHA");
            return contactList;
        }

        for (Integer i=1; i < 20;i++) {
            try {
                Elements resultLink = doc.select("div.box:nth-child(" + i + ") > h2:nth-child(3) > a:nth-child(1)");
                //System.out.println("----------" + i + ": \n" + resultLink.attr("href"));
                System.out.print("-");

                Contact contact = new Contact();

                Document detail = Jsoup.connect("http://www.annonce.cz" + resultLink.attr("href")).get();
                contact.setText(detail.select(".ad-desc").text());
                contact.setDomain(detail.select("#content > div.join.e-rside > div > div.t.p100.d2-table-destroy > div > div.x.w540.d1-fullwidth.d2-fullwidth > div > table > tbody > tr:nth-child(2) > td > a").text());
                contact.setProfession(detail.select("#content > div.join.e-rside > div > div.t.p100.d2-table-destroy > div > div.x.w540.d1-fullwidth.d2-fullwidth > div > table > tbody > tr:nth-child(3) > td").text());
                contact.setRegion(detail.select("#content > div.join.e-rside > div > div.t.p100.d2-table-destroy > div > div.x.w540.d1-fullwidth.d2-fullwidth > div > table > tbody > tr:nth-child(7) > td > a").text());
                contact.setEmail(detail.select("a.form-overlay:nth-child(2)").text());
                contact.setMobil(detail.select("#contact > div > div > div > div:nth-child(1) > div.mrg-top > a").text());

                if (contact.email!=null && !contact.email.equals("")) {
                    contactList.add(contact);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        return contactList;
    }
    @Override
    public List<Contact> parse(AnnonceSettings annonceSettings) throws Exception {
        //String basicUrl = "http://www.annonce.cz/hledam-praci-chci-vydelat$18.html?department=&zps=&location_country=&q=&jobsSourceRejected=1&maxAge=&nabidkovy=0&action=Hledej&page=";
        String basicUrl = annonceSettings.getUrl().replaceAll("\\d+$",""); // remove numbers at the end
        Elements nextTab = null;
        ArrayList<Contact> contacts = new ArrayList<>();
        Integer tabIndex = annonceSettings.getStartAtTab();
        Document doc = null;
        int i=1;
        Integer proxyServerId = 0;
        Server server;

        do {

            String url = basicUrl + tabIndex;
            // No proxy:
            // doc = Jsoup.connect(url).get();

            // Proxy:
            URL Ourl = new URL(url);
            server = annonceSettings.getProxyServerList().getList().get(proxyServerId);
            System.out.println("Parsing through proxy server " + server.getIp() + ":" + server.getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(server.getIp(), server.getPort())); // or whatever your proxy is
            HttpURLConnection uc = (HttpURLConnection)Ourl.openConnection(proxy);
            uc.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");

            System.out.println("Trying to connect tab nr." + tabIndex + ", url: " + url);
            uc.connect();
            System.out.println("Done.");
            System.out.println("HTTP code: " + uc.getResponseCode());

            String line = null;
            StringBuffer tmp = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            tabIndex++;
            while ((line = in.readLine()) != null) {
                tmp.append(line);
            }

            doc = Jsoup.parse(String.valueOf(tmp));


            if (doc.select("#content > div > div > div.x.w630.e-w930 > form > div > p").text().equals("Požadavkům neodpovídají žádné inzeráty."))
                break;


            List<Contact> oneTabContacts = parseTab(url);

            int repeats = 0;
            while (oneTabContacts.size()==0 & repeats < 3)
                {
                    tabIndex--;
                    proxyServerId++;
                    server = annonceSettings.getProxyServerList().getList().get(proxyServerId);
                    System.out.println("No records, switching server to " + server.getIp() + ":" + server.getPort());
                    oneTabContacts = parseTab(url);
                }
            if (oneTabContacts.size()==0) {
                System.out.println("\nNo records in tab\n");
            } else {
                contacts.addAll(oneTabContacts);
            }
        i++;
        } while (annonceSettings.getNrToScrape() > i*20);
        return contacts;
    }

}


