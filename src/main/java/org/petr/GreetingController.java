package org.petr;

import org.petr.parsers.AnnonceSettings;
import org.petr.parsers.Contact;
import org.petr.parsers.IParser;
import org.petr.parsers.Theme;
import org.petr.proxy.ProxyServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    @Qualifier(value = "stub")
    IParser parser;

    AnnonceSettings annonceSettings;

    public GreetingController(){
        AnnonceSettings ac = new AnnonceSettings();
        ac.setUrl("http://www.annonce.cz/hledam-praci-chci-vydelat$18.html?department=&zps=&location_country=&q=&jobsSourceRejected=1&maxAge=&nabidkovy=0&action=Hledej&page=1");
        ac.setNrToScrape(10);
        ac.setStartAtTab(1);
        this.annonceSettings = ac;
    }

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping("/index")
    public String index(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        ArrayList<Theme> themelist = new ArrayList<>();
        themelist.add(new Theme("a","adam"));
        themelist.add(new Theme("b","beda"));
        model.addAttribute("themeList", themelist);
        return "index";
    }

    @RequestMapping("/annonce")
    public String annonce(@ModelAttribute("annosett") AnnonceSettings as, ModelMap model) throws Exception {

        model.addAttribute("annonceSettings",annonceSettings);

        List<Contact> contactList = new ArrayList<>();
        return "annonce";
    }

    @RequestMapping(value = "/scrapeannonce", method = RequestMethod.POST)
    public String scrapeannonce(@ModelAttribute("annosett") AnnonceSettings as, ModelMap model) throws Exception {

        model.addAttribute("annonceSettings",annonceSettings);

        List<Contact> contactList = new ArrayList<>();
        ProxyServerList proxyServerList = new ProxyServerList();
        annonceSettings.setProxyServerList(proxyServerList);
        model.addAttribute("contactList", parser.parse(annonceSettings));
        return "annonce";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(@ModelAttribute("annosett") AnnonceSettings annonceSettings, ModelMap model) throws Exception {

        return "about";
    }

        @RequestMapping(value = "/saveannoncesettings", method=RequestMethod.POST)
        public String saveannoncesettings(@Valid @ModelAttribute("annosett") AnnonceSettings as, BindingResult bindingResult, ModelMap model) {

            if (bindingResult.hasErrors()) {
                System.out.println("binding vole error");
            }
            annonceSettings = as;
            model.addAttribute("annonceSettings",annonceSettings);
            System.out.println("SAVED POST");
            System.out.println(annonceSettings.getUrl());
            return "annonce";
        }


}