<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import = "GeneralClasses.VerifyEmail" %>
<%@page import = "JDBC.ValidateUser" %>
<%@page import = "java.io.PrintWriter" %>
<%@page import = "JDBC.DatabaseConnection" %>
<%@page import = "java.sql.*" %>     
     
<% 
    String NewPassword = new String();
    String PasswordChangeRequestEmail;
    
	PrintWriter Out = response.getWriter();
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	
    PasswordChangeRequestEmail = request.getParameter("Username");   
    
    boolean VerifyEmailExistsOrNot = ValidateUser.validate(PasswordChangeRequestEmail);
    System.out.println(VerifyEmailExistsOrNot);
  	//System.out.println(RegisterUserEmail);
  	
		if(VerifyEmailExistsOrNot == true)
		{
			NewPassword = VerifyEmail.emailVerification(PasswordChangeRequestEmail);
			System.out.println(NewPassword);
			//DatabaseConnection.databaseConn();
			preparedStatement = DatabaseConnection.con.prepareStatement("UPDATE DVS SET PASSWORD = ? WHERE USERNAME=?");
			preparedStatement.setString(1,NewPassword);
			preparedStatement.setString(2,PasswordChangeRequestEmail);
			resultSet=preparedStatement.executeQuery();

		}
		else
		{			
			Out.print("Username does not exists");
			Out.print("</br></br><a href=http://localhost:8080/DVS-Projects/Login.jsp>Go back to Login</a>");	
			Out.print("</br></br><a href=http://localhost:8080/DVS-Projects/NewUser.jsp>Go back to Register</a>");
		}
%>
<% String RegisterUserEmail1 = (String) session.getAttribute("RegisterUserEmail");%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Login-Register.css">
		<title>Email sent</title>
	</head>
	<body>
<%if(VerifyEmailExistsOrNot == true){%>
		<div id="id01">
  
  			<form class="modal-content animate" method="post">
   				<div class="container">     
      				<p>Email sent successfully!!</p>
      				<a href ="http://localhost:8080/DVS-Projects/Login.jsp">Back to Login</a>    
    			</div>
  			</form>
		</div>
<%}%>
	</body>
</html>