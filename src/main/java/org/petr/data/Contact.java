package org.petr.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by petr on 13.9.16.
 */

@Entity
@IdClass(value = org.petr.data.ContactPK.class)
public class Contact implements Serializable {

    @Id
    String email;
    String text;
    String mobil;
    String domain;
    String profession;
    String region;

    @Id
    Date date;
    Date lastMailSent;
    String status;
    Boolean toBeDeleted;

    public Contact(String email, String text, String mobil, String domain, String profession, String region, Date date) {
        this.email = email;
        this.text = text;
        this.mobil = mobil;
        this.domain = domain;
        this.profession = profession;
        this.region = region;
        this.date = date;
        this.lastMailSent = null;
        this.status = "";
        this.toBeDeleted = false;
    }

    public Contact(String email, String text, String mobil, String domain, String profession, String region, Date date, Date lastMailSent, String status) {
        this.email = email;
        this.text = text;
        this.mobil = mobil;
        this.domain = domain;
        this.profession = profession;
        this.region = region;
        this.date = date;
        this.lastMailSent = lastMailSent;
        this.status = status;
    }

    public Contact() {
    }

    public Boolean getToBeDeleted() {
        return toBeDeleted;
    }

    public void setToBeDeleted(Boolean toBeDeleted) {
        this.toBeDeleted = toBeDeleted;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLastMailSent() {
        return lastMailSent;
    }

    public void setLastMailSent(Date lastMailSent) {
        this.lastMailSent = lastMailSent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDomain() {
        return domain;
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
    public String toString() {
        return email + " " + mobil + " " + text + "\n";
    }
}
