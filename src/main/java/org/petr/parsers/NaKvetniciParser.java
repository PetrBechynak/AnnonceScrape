package org.petr.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.petr.data.MenuRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.time.LocalDate.now;

/**
 * Created by petr on 13.9.16.
 */

 @Component(value = "fullNaKvetnici")
 @ApplicationScope
public class NaKvetniciParser implements IParser {
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
        String url = "https://www.nakvetnici.cz/";
        Document doc = Jsoup.connect(url).get();
        ArrayList<String> records = new ArrayList<>();
        Element table = null;
            if (!doc.select("#rt-feature > div > div.rt-grid-7.rt-alpha > div:nth-child(2) > div > div.module-content > div > table").text().equals("")){
                    table = doc.select("#rt-feature > div > div.rt-grid-7.rt-alpha > div:nth-child(2) > div > div.module-content > div > table").get(0);
            } else {
                if(!doc.select("#rt-feature > div > div.rt-grid-7.rt-alpha > div:nth-child(3) > div > div.module-content > div > table").text().equals("")){
                    table = doc.select("#rt-feature > div > div.rt-grid-7.rt-alpha > div:nth-child(3) > div > div.module-content > div > table").get(0);
                } else {
                    throw new Exception("Chyba parsovani");
                }
        }

        Elements rows = table.select("tr");

        for (int i = 1; i <= rows.size(); i++) { //first row is the col names so skip it.
            MenuRecord menuRecord = new MenuRecord();
            menuRecord.setText(  table.select("tbody > tr:nth-child("+ i +") > td:nth-child(1)").text());
            menuRecord.setPrice( table.select("tbody > tr:nth-child("+ i +") > td:nth-child(2) > strong").text());
            System.out.println(menuRecord);
            menu.add(menuRecord);
        }

    }

}


