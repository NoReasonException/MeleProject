package BackEndSystem.Entities.Email;
import javax.activation.*;
import javax.mail.*;
import java.util.*;
import javax.mail.internet.*;

public class SendEmailMain {
    private static java.lang.String From = "University@gmail.com";
    public static boolean SendEmail(java.lang.String to,java.lang.String Subject,java.lang.String message){
        java.lang.String host="smtp.gmail.com";
        Properties defaultProperties = new Properties(); //Παρε enviromental variables
        defaultProperties.setProperty("mail.smtp.starttls.enable", "true");
        defaultProperties.setProperty("mail.smtp.host", host); //πρόσθεσε το config(ρωτα μελε) 
        defaultProperties.setProperty("mail.smtp.port", "587");
        defaultProperties.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(defaultProperties,new javax.mail.Authenticator() {
               @Override
               protected PasswordAuthentication getPasswordAuthentication(){
                   return new PasswordAuthentication("stefan1998xd@gmail.com", "102030102030102030");
               }
        
        });
        try{
            MimeMessage ea = new MimeMessage(session);
            ea.setFrom(new InternetAddress(From));
            ea.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            ea.setSubject(Subject);
            ea.setText(message);
            Transport.send(ea);
            
            
        }catch(MessagingException e){
            System.out.print(e);
            return false;
            
        }
        return true;
        
    }
}
