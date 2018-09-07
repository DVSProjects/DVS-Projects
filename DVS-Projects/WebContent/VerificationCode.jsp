<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "GeneralClasses.VerifyEmail" %>

  <%@page import = "javax.mail.*" %>          
     <%@page import = "javax.mail.internet.*" %>            
            
    
    
    <% String RegisterUserEmail,Firstname,Lastname;
    RegisterUserEmail = request.getParameter("EmailUsername");
    Firstname = request.getParameter("Firstname");
    Lastname = request.getParameter("Lastname");		  

    new VerifyEmail();
    
    session.setAttribute("RegisterUserEmail",RegisterUserEmail ); 
    session.setAttribute("Firstname",Firstname );
    session.setAttribute("Lastname",Lastname);
  
    %>
    <% String RegisterUserEmail1 = (String) session.getAttribute("RegisterUserEmail");
        		  System.out.print(RegisterUserEmail1);
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Login-Register.css">

<title>Insert title here</title>
</head>
<body>
<div id="id02">
  
  <form class="modal-content animate" action="http://localhost:8080/DVS-Projects/RegisterUser" method="post">
  

    <div class="container">
    <p>Verification code was sent to</p></b></b>
     <p><%out.println(RegisterUserEmail1); %></p>
      <label for="vc"><b>Verification Code</b></label>
      <input type="text" placeholder="Enter verification code" name="Verification" required>
     
      <button type="submit">Verify</button>
      <a href="http://localhost:8080/DVS-Projects/VerificationCode.jsp">Resend code</a>
    </div>
  </form>
</div>
</body>
</html>