package org.petr.parsers;

/**
 * Created by petr on 15.9.16.
 */
public class Theme {
    String id;
    String theme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Theme(String id, String theme) {
        this.id = id;
        this.theme = theme;
    }
}
