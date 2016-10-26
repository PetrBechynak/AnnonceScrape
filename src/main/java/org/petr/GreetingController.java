package org.petr;

import com.google.common.collect.Lists;
import org.petr.data.Contact;
import org.petr.data.ContactList;
import org.petr.data.ContactRepository;
import org.petr.parsers.AnnonceSettings;
import org.petr.parsers.IParser;
import org.petr.proxy.ProxyServerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {
    @Autowired
    @Qualifier(value = "stub")
    IParser parser;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactList contactList;

    AnnonceSettings annonceSettings;

    public GreetingController() {
        AnnonceSettings ac = new AnnonceSettings();
        ac.setUrl("http://www.annonce.cz/hledam-praci-chci-vydelat$18.html?department=&zps=&location_country=&q=&jobsSourceRejected=1&maxAge=&nabidkovy=0&action=Hledej&page=1");
        ac.setNrToScrape(10);
        ac.setStartAtTab(1);
        this.annonceSettings = ac;
    }

    @RequestMapping("/annonce")
    public String annonce(@ModelAttribute("annosett") AnnonceSettings as, ModelMap model) throws Exception {

        model.addAttribute("annonceSettings", annonceSettings);

        List<Contact> contactList = new ArrayList<>();
        return "annonce";
    }

    @RequestMapping(value = "/scrapeannonce", method = RequestMethod.POST)
    public String scrapeannonce(@ModelAttribute("annosett") AnnonceSettings as, ModelMap model) throws Exception {

        model.addAttribute("annonceSettings", annonceSettings);
        ProxyServerList proxyServerList = new ProxyServerList();
        annonceSettings.setProxyServerList(proxyServerList);
        parser.fillList(annonceSettings);
        List<Contact> list = parser.getList();
        model.addAttribute("contactList", list);
        contactRepository.save(list);

        return "annonce";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about(@ModelAttribute("annosett") AnnonceSettings annonceSettings, ModelMap model) throws Exception {

        return "about";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(@ModelAttribute("annosett") AnnonceSettings annonceSettings, ModelMap model) throws Exception {

        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@ModelAttribute("annosett") AnnonceSettings annonceSettings, ModelMap model) throws Exception {

        return "index";
    }

    @RequestMapping(value = "/deleteAllSaved", method = RequestMethod.POST)
    public String deleteAllSaved() throws Exception {
        contactRepository.deleteAll();
        return "saved";
    }

    @RequestMapping(value = "/deleteSaved", method = RequestMethod.POST)
    public String deleteMarked(ModelMap model, @ModelAttribute("contactList") ContactList contactList, BindingResult bindingResult) throws Exception {

        // contactRepository.save(list);
        contactRepository.deleteMarked();
        contactList = new ContactList(Lists.newArrayList(contactRepository.findAll()));
        model.addAttribute("contactList", contactList);
        return "saved";
    }


    @RequestMapping(value = "/saved", method = RequestMethod.GET)
    public String saved(ModelMap model) throws Exception {
        contactList = new ContactList(Lists.newArrayList(contactRepository.findAll()));
        model.addAttribute("contactList", contactList);
        return "saved";
    }

    @RequestMapping(value = "/saveannoncesettings", method = RequestMethod.POST)
    public String saveannoncesettings(@Valid @ModelAttribute("annosett") AnnonceSettings as, BindingResult bindingResult, ModelMap model) {

        if (bindingResult.hasErrors()) {
            System.out.println("binding vole error");
        }
        annonceSettings = as;
        model.addAttribute("annonceSettings", annonceSettings);
        System.out.println("SAVED POST");
        System.out.println(annonceSettings.getUrl());
        return "annonce";
    }


}