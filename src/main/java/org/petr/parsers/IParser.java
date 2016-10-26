package org.petr.parsers;

import org.petr.data.Contact;

import java.io.Serializable;
import java.util.List;

/**
 * Created by petr on 13.9.16.
 */
public interface IParser extends Serializable {
    List<Contact> getList();

    List<Contact> fillAndGetList(AnnonceSettings a) throws Exception;

    void fillList(AnnonceSettings a) throws Exception;

}
