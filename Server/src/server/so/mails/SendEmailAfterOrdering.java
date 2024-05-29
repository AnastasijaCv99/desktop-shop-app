/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.so.mails;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import zcommon.domain.Order;

/**
 *
 * @author Korisnik
 */
public class SendEmailAfterOrdering {
    
    
    public void send(String nameOfPdf, Order order) {

        // Recipient's email ID needs to be mentioned.
        //CHANGE
        String to = order.getUserID().getAddress();

        // Sender's email ID needs to be mentioned
        String from = "ana249cvetkovic@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        //properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //properties.put("mail.smtp.ssl.enable", "true");

        //properties.put("mail.smtp.socketFactory.port", "465");
        //properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //properties.put("mail.protocol.ssl.trust", host);
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ana249cvetkovic@gmail.com", "hbugyisgusvdgkxq");
            }
        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Thank you for purchasing at our store!");

            // Now set the actual message
            //message.setText("Dear XX \nThank you for choosing our store for your purchase.\nWe can't wait to see you again!\nYou can find a receipt of the purchase attached in this email.");

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Dear " + order.getUserID().getName() + ",\nThank you for choosing our store for your purchase.\nWe can't wait to see you again!\nYou can find a receipt of the order attached in this email.");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            //String filename = "D:\\Projekti\\NetBeansProjects\\PROJEKAT\\Anastasija Cvetkovic\\PDFs\\" + namePdf + ".pdf";
            //String filename = namePdf;
            DataSource source = new FileDataSource(nameOfPdf);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("Order " + order.getOrderID() + ".pdf");
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
