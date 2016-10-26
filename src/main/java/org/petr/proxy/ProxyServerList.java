package org.petr.proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petr on 20.9.16.
 */
public class ProxyServerList {
    List<Server> list = new ArrayList();

    public ProxyServerList() throws IOException {
        System.out.println("Loading list of proxy servers...");
        fill();
        System.out.println("Loading list of proxy servers sucessfully ended.");
    }

    public static void main(String[] args) throws IOException {
        System.out.println("START");
        ProxyServerList ps = new ProxyServerList();

        ps.fill();
        ps.toString();
    }

    public List<Server> getList() {
        return list;
    }

    public void fill() throws IOException {
        Document doc = Jsoup.connect("https://incloak.com/proxy-list/?type=h&anon=34#list").get();
        Integer i;
        List<Server> clist = new ArrayList<>();

        for (i = 1; i < 5; i++) {

            try {
                Server server = new Server();
                String ip;
                String port;
                String type;
                String anonymity;

                ip = doc.select("#content-section > section.proxy > div > table > tbody > tr:nth-child(" + i + ") > td.tdl").text();
                port = doc.select("#content-section > section.proxy > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(2)").text();
                type = doc.select("#content-section > section.proxy > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(5)").text();
                anonymity = doc.select("#content-section > section.proxy > div > table > tbody > tr:nth-child(" + i + ") > td:nth-child(6)").text();


                server.setIp(ip);
                server.setPort(port);
                server.setType(type);
                server.setAnonymity(anonymity);
                System.out.print("-");

                clist.add(server);

            } catch (Exception e) {

            }
        }
        System.out.println(clist);
        list = clist;
    }

}

