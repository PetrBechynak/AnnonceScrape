package org.petr.data;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by petr on 24.10.16.
 */
public class ContactPK implements Serializable {
    String email;
    Date date;

    public ContactPK() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ContactPK) {
            ContactPK contactPK = (ContactPK) obj;

            if (!contactPK.getEmail().equals(email)) {
                return false;
            }

            if (!contactPK.getDate().equals(date)) {
                return false;
            }

            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return email.hashCode() + date.hashCode();
    }
}

