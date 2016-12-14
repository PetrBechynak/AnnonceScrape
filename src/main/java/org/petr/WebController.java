package org.petr;

import org.petr.data.Menu;
import org.petr.data.MenuRecord;
import org.petr.data.Message;
import org.petr.parsers.IParser;
import org.petr.parsers.PaloucekParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@CrossOrigin
public class WebController {

    @Autowired
    @Qualifier(value = "stub")
    IParser paloucekParser;

    @Autowired
    @Qualifier(value = "stubnakvetnici")
    IParser naKvetniciParser;

    @RequestMapping("/paloucek")
    public Message getPaloucekMenu() throws Exception {
        ArrayList<MenuRecord> paloucekMenu = paloucekParser.getMenu();
        Message m = new Message(paloucekMenu);
        return m;
    }

    @RequestMapping("/nakvetnici")
    public Message getNaKvetniciMenu() throws Exception {
        ArrayList<MenuRecord> paloucekMenu = naKvetniciParser.getMenu();
        Message m = new Message(paloucekMenu);
        return m;
    }

    @RequestMapping("/menurecord")
    public MenuRecord menuRecord() throws Exception {
        MenuRecord mr = new MenuRecord("a","b","c");
        return mr;
    }

    @RequestMapping("/test")
    public String test() throws Exception {
        Integer r = new Random().nextInt();
        return "{\"message\":\"Hello world voe! " + r + "\"}" ;
    }

    @RequestMapping("/test2")
    public String test2() throws Exception {
        Double r = new Random().nextDouble();
        return "{\"message\":\"Hello world, please! " + r + "\"}";
    }

}