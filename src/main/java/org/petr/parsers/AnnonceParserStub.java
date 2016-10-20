package org.petr.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petr on 13.9.16.
 */

@Component(value="stub")
public class AnnonceParserStub implements IParser {


    public List<Contact> parse(AnnonceSettings as) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();
        Contact contact = new Contact("asdf","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur quis tempus lorem. Donec dapibus malesuada arcu, eget auctor orci sodales vitae. Integer varius aliquet lacus nec maximus. Duis sollicitudin erat purus. Morbi fermentum, justo at imperdiet euismod, ex ex pretium odio, sed scelerisque tellus magna id orci. Proin augue ex, ullamcorper vitae tincidunt eu, aliquet at velit. Curabitur lobortis eros at augue placerat imperdiet. I","dfgh","fghj","ghjk","Praha");
        contacts.add(contact);
        contact = new Contact("asdffd","Sed rutrum massa vel ex ullamcorper luctus. Praesent aliquam erat sed mauris fringilla, ut condimentum massa fringilla. Morbi ultricies, lectus eget sollicitudin luctus, elit nulla ultricies risus, in mattis arcu massa a sem. Nullam nunc odio, porta non odio sed, viverra efficitur mauris.","defgh","fghj","ghjk","ghjk");
        contacts.add(contact);
        contact = new Contact("asdf","In sodales eget lectus non ullamcorper. Aenean mauris tellus, auctor sed ipsum a, tincidunt laoreet ex. Aenean vitae leo in metus vehicula cursus auctor a dui.","dfgh","fghj","168684","ghjk");
        contacts.add(contact);

        return contacts;
    }

}


