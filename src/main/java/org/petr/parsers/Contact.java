package org.petr.parsers;

/**
 * Created by petr on 13.9.16.
 */
public class Contact {
    String email;
    String text;
    String mobil;
    String domain;
    String profession;
    String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDomain() {
        return domain;
    }

    public Contact(String email, String text, String mobil, String domain, String profession, String region) {
        this.email = email;
        this.text = text;
        this.mobil = mobil;
        this.domain = domain;
        this.profession = profession;
        this.region = region;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public Contact(String email, String text, String mobil) {
        this.email = email;
        this.text = text;
        this.mobil = mobil;
    }

    public Contact() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    @Override
    public String toString(){
        return email + " " + mobil + " " + text + "\n";
    }
}
