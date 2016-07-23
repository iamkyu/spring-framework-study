package springbook.user.service;


import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Kj Nam
 * @since 2016-07-23
 */
public class DummyMailSender implements MailSender {
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {

    }

    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
