package org.petr.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.petr.data.Menu;
import org.petr.data.MenuRecord;
import org.petr.proxy.Server;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;

/**
 * Created by petr on 13.9.16.
 */

 @Component(value = "full")
 @ApplicationScope
public class PaloucekParser implements IParser {
    private ArrayList<MenuRecord> menu = new ArrayList<>();
    private LocalDate date=null;


    @Override
    public ArrayList<MenuRecord> getMenu() throws Exception {
        if (date!=null && menu!=null)
        return menu;

        fillMenu();
        return menu;
    }

    @Override
    public void fillMenu() throws Exception {
        date = now();
        String url = "http://www.restpaloucek.cz/jidelnilistek/poledni%20nabidka/";
        Document doc = Jsoup.connect(url).get();

            if (doc.select("#page-content > table > tbody").text().equals(""))
                throw new Exception("Chyba parsovani");


        ArrayList<String> records = new ArrayList<>();
        Element table = doc.select("#page-content > table > tbody").get(0);
        Elements rows = table.select("tr");

        for (int i = 1; i <= rows.size(); i++) { //first row is the col names so skip it.
            MenuRecord menuRecord = new MenuRecord();
            menuRecord.setMetric(table.select("tr:nth-child("+ i +") > td:nth-child(1) > h3 > span").text());
            menuRecord.setText(  table.select("tr:nth-child("+ i +") > td:nth-child(2) > h3 > span").text());
            menuRecord.setPrice( table.select("tr:nth-child("+ i +") > td:nth-child(3) > h3 > span").text());
            System.out.println(menuRecord);
            menu.add(menuRecord);
        }

    }

}


