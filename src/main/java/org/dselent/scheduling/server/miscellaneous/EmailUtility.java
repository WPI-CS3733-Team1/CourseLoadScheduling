package org.dselent.scheduling.server.miscellaneous;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailUtility
{
	
	public static String USERNAME = "";
	public static String PASSWORD = "";
	
	public static void setEmailUsername(String username) {
		USERNAME = username;
	}
	
	public static void setEmailPassword(String password) {
		PASSWORD = password;
	}
	
	
	public static boolean sendEmail(String recipient, String cc, String title, String content)
	{
		String[] recipients = new String[1];
		recipients[0] = recipient;
		
		 return sendEmail(recipients, cc, title, content);
	}
    
    public static boolean sendEmail(String[] recipients, String cc, String title, String content)
    {
        
 //       String username = env.getProperty(AppConfig.EMAIL_USERNAME);
   //     String password = env.getProperty(AppConfig.EMAIL_PASSWORD);
        
        //System.out.println("properties file: "+p.getProperty("email_username"));
       
        //TODO
        //CAN THIS BE DONE WITH LAMBA EXPRESSION?
        //IT LOOKS HIDEOUS
        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");
    	
    	
    	
        Session session = Session.getDefaultInstance(
            p, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            }
        );

        
        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            
            for(int i=0; i<recipients.length; i++)
            {
            	// should be recipient
            	message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients[i]));
            }
            
            message.setSubject(title);
            message.setText(content, "utf-8", "html");
            
            Transport.send(message);

        }
        catch (MessagingException e)
        {
            e.printStackTrace();
        }

        return true;
    }

}
