<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Login-Register.css">
		<title>Password reset</title>
	</head>
	<body>
		<div id="id01"> 
  			<form class="modal-content animate" action="http://localhost:8080/DVS-Projects/EmailSentIndication.jsp" method="post">
	    		<div class="container">
	      			<label for="uname"><b>Username</b></label>
	      			<input type="text" placeholder="Enter Username" name="Username" required>     
	      			<button type="submit">Reset Password</button></br></br>     
	    		</div>
  			</form>
		</div>
	</body>
</html>