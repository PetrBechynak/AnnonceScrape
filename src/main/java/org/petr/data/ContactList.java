package org.petr.data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by petr on 24.10.16.
 */
@Component
public class ContactList {
    public ArrayList<Contact> list;

    public ContactList(ArrayList<Contact> list) {
        this.list = list;
    }

    public ContactList() {
    }

    public ArrayList<Contact> getList() {
        return list;
    }

    public void setList(ArrayList<Contact> list) {
        this.list = list;
    }
}
