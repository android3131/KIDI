package com.example.demo.backend2;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmailToClient {

    public static void sendEmail(String recipient){
        System.out.println("Sending an Email to client..");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host","smtp.google.com");
        properties.put("mail.smtp.port","587");


        String myAccount = "ahmedjabareen7@gmail.com";
        String pwd = "************";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount,pwd);
            }
        });

        Message message = prepareMessage(session,myAccount,recipient);
    }

    private static Message prepareMessage(Session session, String myEmail,String recipient) {

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Welcome To KIDI Course "+ "CourseName");
            message.setText("Hey There! \n Welcome to KIDI Course: "+"CourseName");
            return message;
        }catch(Exception e){
            Logger.getLogger(SendEmailToClient.class.getName()).log(Level.SEVERE,null,e);
            e.printStackTrace();
        }
        return null;
    }
}
