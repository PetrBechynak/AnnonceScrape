package org.petr.parsers;

import org.petr.data.Contact;
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
public class AnnonceParserStub implements IParser {
    private List<Contact> list;

    public List<Contact> getList() {
        return list;
    }

    public List<Contact> fillAndGetList(AnnonceSettings ac) throws Exception {
        fillList(ac);
        return list;
    }

    public void fillList(AnnonceSettings as) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact("asdf" + new Random().nextInt(10000), "Lorem ipsum dolor sit amet, consectetur adigh", "fghj", "ghjk", "ddsa", "Praha",
                Date.valueOf(LocalDate.now().plusDays(1)));
        contacts.add(contact);
        contact = new Contact("asdffd" + new Random().nextInt(10000), "Sed rutingilla, ut sed, viverra efficitur mauris.", "defgh", "fghj", "ghjk", "ghjk",
                Date.valueOf(LocalDate.now()));
        contacts.add(contact);
        contact = new Contact("asdf" + new Random().nextInt(10000), "In sodales eget lecan vitae leo in metus vehicula cursus auctor a dui.", "dfgh", "fghj", "168684", "ghjk",
                Date.valueOf(LocalDate.now().minusDays(1)));
        contacts.add(contact);

        list = contacts;
    }

}


