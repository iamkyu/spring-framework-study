package springbook.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author Kj Nam
 * @since 2016-12-01
 */
public class DummyMailSender implements MailSender {
    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
