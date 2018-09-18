<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "GeneralClasses.VerifyEmail"%>
<%@page import = "JDBC.ValidateUser"%>
<%@page import = "java.io.PrintWriter"%>

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
  	boolean display = false;
		if(VerifyEmailExistsOrNot == false)
		{
			try
			{
	    		VerificationCode = VerifyEmail.emailVerification(RegisterUserEmail);
				System.out.println(VerificationCode);
			    session.setAttribute("RegisterUserEmail",RegisterUserEmail ); 
			    session.setAttribute("Firstname",Firstname );
			    session.setAttribute("Lastname",Lastname);
			    session.setAttribute("Password",Password);
			}
			catch(Exception e)
			{
				Out.print("Please enter the correct email address");
				Out.print("</br></br><a href=http://localhost:8080/DVS-Projects/NewUser.jsp>Go back</a>");	
				display = true;
			}
		}
		else
		{			
			Out.print("Username/Password already exists");
			Out.print("</br></br><a href=http://localhost:8080/DVS-Projects/Login.jsp>Go back to Login</a>");
			display = true;
		}
  
%>
<% String RegisterUserEmail1 = (String) session.getAttribute("RegisterUserEmail");%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Login-Register.css">
		<title>Insert title here</title>
	</head>
<%if(display == false){%>
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
<%}%>
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