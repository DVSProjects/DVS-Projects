<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "GeneralClasses.VerifyEmail" %>
    <%@page import = "JDBC.ValidateUser" %>
         <%@page import = "java.io.PrintWriter" %>
               
    <% 
    String VerificationCode = new String();
    String RegisterUserEmail,Firstname,Lastname, Password;
	PrintWriter Out = response.getWriter();
    
    RegisterUserEmail = request.getParameter("EmailUsername");   
    Firstname = request.getParameter("Firstname");
    Lastname = request.getParameter("Lastname");	
    Password = request.getParameter("Password");	

    
    boolean VerifyEmailExistsOrNot = ValidateUser.validate(RegisterUserEmail);
    System.out.println(VerifyEmailExistsOrNot);
  	//System.out.println(RegisterUserEmail);
  	
		if(VerifyEmailExistsOrNot == true)
		{
    		VerificationCode = VerifyEmail.emailVerification(RegisterUserEmail);
			System.out.println(VerificationCode);
		    session.setAttribute("RegisterUserEmail",RegisterUserEmail ); 
		    session.setAttribute("Firstname",Firstname );
		    session.setAttribute("Lastname",Lastname);
		    session.setAttribute("Password",Password);

		}
		else
		{			
			Out.print("Username/Password already exists");
			Out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/Login.jsp>Go back to Login</a>");		
		}
  
    %>
    <% String RegisterUserEmail1 = (String) session.getAttribute("RegisterUserEmail");
        		 // System.out.print(RegisterUserEmail1);
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
      <input type="text" id=verify placeholder="Enter verification code" name="Verification" onkeyup='check();' required>
      
        <span id='message'></span><br>   
           
      <button type="submit" id="submit">Verify</button>
      
      <a href="http://localhost:8080/DVS-Projects/VerificationCode.jsp">Resend code</a>
      
    </div>
  </form>
</div>
</body>
<script>
var check = function()
{
	<%String code = VerificationCode;%>
var verificationcode = "<%=code%>";
  if (verificationcode == document.getElementById('verify').value)
  {   
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'Verified';
     document.getElementById('submit').disabled = false;
  }
  else 
  {
     
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'Not Verified';
     document.getElementById('submit').disabled = true;
  }
}
</script>
</html>