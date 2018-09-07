package GeneralClasses;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class VerifyEmail
{
	//public static void main(String []args) throws UnsupportedEncodingException
	public static void emailVerification(String CodeToBeSent) 
	{
		String Email = CodeToBeSent;
		System.out.println(Email);

		final String EmailSender = "sridhara.r@avankia.com";
        final String Emailpassword = "Avanki@21";
        final String host = "smtp.gmail.com" ;

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.user", EmailSender);
        props.put("mail.smtp.password", Emailpassword);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //props.put("mail.debug", "true");
        

        Session session = Session.getInstance(props,new javax.mail.Authenticator() 
        {
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(EmailSender, Emailpassword);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("DVS", "no-reply@dvs.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(Email));
            String RandomCode = RandomStringValues.randomValues();
            message.setSubject("Email Verification");
            message.setText("Hi,"+ "\n\n This is an auto generated email\n\n Your Verification code is : "+RandomCode);
           
            Transport transport = session.getTransport("smtp");
            transport.connect(host, EmailSender, Emailpassword);
            Transport.send(message);

            System.out.println("Done");
         

        }
        catch (MessagingException e)
        {
            throw new RuntimeException(e);
        } 
        catch (UnsupportedEncodingException e) 
        {
			
			e.printStackTrace();
		}
	}

}
