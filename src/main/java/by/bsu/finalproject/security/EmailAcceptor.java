package by.bsu.finalproject.security;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Email sender
 * @author A. Kuzmik
 */

public class EmailAcceptor {

    private static final String SENDER_EMAIL_ADDRESS = "artemresale@gmail.com";
    private static final String SENDER_EMAIL_PASSWORD = "199788Art";
    private static final String SENDER_HOST = "smtp.gmail.com";
    private static final String SENDER_PORT = "587";

    /**
     * Sends code to user email
     * @param email
     * @return accept number
     * */

    public String sendMessage(String email) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", SENDER_HOST);
        properties.put("mail.smtp.port", SENDER_PORT);
        properties.put("mail.from", SENDER_EMAIL_ADDRESS);
        properties.put("mail.smtp.password", SENDER_EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });
        String number = String.valueOf((int)(30 + Math.random()*30));

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("accept number");
        message.setText(number);
        Transport.send(message);
        return number;

    }
}
