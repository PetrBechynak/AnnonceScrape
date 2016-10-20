package org.petr.mailers;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * Created by petr on 15.9.16.
 */
public interface Imailer {
    public void send() throws MessagingException;
}
