<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="Login-Register.css">
		<title>Login</title>
	</head>
	<body>
		<div id="id01">
  			<form class="modal-content animate" action="http://localhost:8080/DVS-Projects/LoginServlet" method="post">
   				<div class="container">
      				<label for="uname"><b>Username</b></label>
      				<input type="text" placeholder="Enter Username" name="Username" required>
      				<label for="psw"><b>Password</b></label>
      				<input type="password" placeholder="Enter Password" name="Password" required>      
      				<button type="submit">Login</button>
				</div>
				<div class="container" style="background-color:#f1f1f1">   
					<a href="http://localhost:8080/DVS-Projects/NewUser.jsp">New User?</a>
					<a href="http://localhost:8080/DVS-Projects/ForgotPassword.jsp">Forgot Password?</a>
    			</div>
  			</form>
		</div>
	</body>
</html>