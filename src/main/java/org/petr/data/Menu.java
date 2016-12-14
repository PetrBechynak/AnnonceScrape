package org.petr.data;

import org.springframework.web.context.annotation.ApplicationScope;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petr on 13.9.16.
 */

@ApplicationScope
public class Menu {
    ArrayList<MenuRecord> records;

    public Menu(ArrayList<MenuRecord> records) {
        this.records = records;
    }

    public ArrayList<MenuRecord> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<MenuRecord> records) {
        this.records = records;
    }



}
