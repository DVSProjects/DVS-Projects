<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>
<%@ page import="javax.activation.*" %>
<%@ page import="GeneralClasses.RandomStringValues" %>
<%@ page import="javax.activation.*" %>
<%@ page import="javax.mail.Transport" %>

<%@ page import="javax.mail.Session" %>

<%@ page import="java.io.UnsupportedEncodingException" %>



<%
HttpSession sessions = request.getSession();
String RegisterUserEmail = (String) session.getAttribute("RegisterUserEmail");
System.out.println(RegisterUserEmail);

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


Session session1 = Session.getInstance(props,new javax.mail.Authenticator() 
{
    protected PasswordAuthentication getPasswordAuthentication() 
    {
        return new PasswordAuthentication(EmailSender, Emailpassword);
    }
  });

try {

    Message message = new MimeMessage(session1);
    message.setFrom(new InternetAddress("DVS", "no-reply@dvs.com"));
    message.setRecipients(Message.RecipientType.TO,
    InternetAddress.parse(RegisterUserEmail));
    String RandomCode = RandomStringValues.randomValues();
    message.setSubject("Email Verification");
    message.setText("Hi,"+ "\n\n This is an auto generated email\n\n Your Verification code is : "+RandomCode);
   
    Transport transport = session1.getTransport("smtp");
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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>