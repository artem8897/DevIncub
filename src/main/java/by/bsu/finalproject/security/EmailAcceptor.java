package by.bsu.finalproject.security;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.PrintWriter;
import java.util.Properties;

public class EmailAcceptor {
    static final String SENDER_EMAIL_ADDRESS = "artemresale2@gmail.com";
    static final String SENDER_EMAIL_PASSWORD = "199788Art";
    static final String SENDER_HOST = "smtp.gmail.com";
    static final String SENDER_PORT = "587";

    public void sendMessage(String email) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SENDER_HOST);
        properties.put("mail.smtp.port", SENDER_PORT);
        properties.put("mail.from", SENDER_EMAIL_ADDRESS);
        properties.put("mail.smtp.password", SENDER_EMAIL_PASSWORD);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
                    }
                });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL_ADDRESS));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));
            message.setSubject("Proselyte Servlets Tutorial");

            message.setText("Send Email Demo");
            Transport.send(message);

        } catch (
                MessagingException e) {
            e.printStackTrace();
        }
    }
}
