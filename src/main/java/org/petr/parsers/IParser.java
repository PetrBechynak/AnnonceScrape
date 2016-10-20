package org.petr.parsers;

import java.io.IOException;
import java.util.List;

/**
 * Created by petr on 13.9.16.
 */
public interface IParser {
    List<Contact> parse(AnnonceSettings a) throws Exception;
}
