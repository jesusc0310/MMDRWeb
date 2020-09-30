/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.notifications;

/**
 *
 * @author Jesus Cruz
 */
import java.io.FileReader;
import java.io.IOException;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    private String host;
    private String username;
    private String password;

    //Constructor

    public Email() {
        
        Properties prop = new Properties();
        
        try {
            prop.load(new FileReader("C:\\Users\\jesus\\Desktop\\DAM\\1DAM\\Programacion\\MisMarcasDeRunning\\src\\main\\webapp\\config\\infoEmail.properties"));
        } catch (IOException e) {
        }
        
        this.host = prop.getProperty("HOST");
        this.username = prop.getProperty("USERNAME");
        this.password = prop.getProperty("PASSWORD");
    }

    // Setter

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method that send Email to the specified receiver, with the specified 
     * subject and which content is an HTML5 web page.
     *
     * @param receiver The person who will receive the mail.
     * @param subject  The subject of the mail.
     * @param html     HTML5 web page that will be the mail content.
     * 
     * @return returns true if the mail was send else it returns false.
     */
    public boolean send(String receiver, String subject, String html) {
        boolean result = false;

        String sender = username;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiver));
            message.setSubject(subject);

            message.setContent(html, "text/html; charset=utf-8");
            Transport.send(message);
            result = true;
        } catch (Exception e) {
            result = false;
            throw new RuntimeException(e);
        }
        return result;
    }
}

