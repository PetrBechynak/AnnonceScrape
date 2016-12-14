package org.petr.parsers;

import org.petr.data.Menu;
import org.petr.data.MenuRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by petr on 13.9.16.
 */

@Component(value = "stub")
@ApplicationScope
public class PaloucekParserStub implements IParser {
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
    public void fillMenu() throws Exception{
        menu.add(new MenuRecord("","Pondeli",""));
        menu.add(new MenuRecord("2kg","koleno veprove","120,-"));
        menu.add(new MenuRecord("15dkg","veprova hlava","110,-"));
        menu.add(new MenuRecord("150g","ovar","50,-"));
        menu.add(new MenuRecord("140g","sunka","40,-"));
    }

}


