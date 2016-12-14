package org.petr.parsers;

import org.petr.data.Menu;
import org.petr.data.MenuRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by petr on 13.9.16.
 */
public interface IParser extends Serializable {
    ArrayList<MenuRecord> getMenu() throws Exception;

    void fillMenu() throws Exception;

}
