package org.petr.mailers;

import javax.mail.MessagingException;

/**
 * Created by petr on 15.9.16.
 */
public interface Imailer {
    public void send() throws MessagingException;
}
