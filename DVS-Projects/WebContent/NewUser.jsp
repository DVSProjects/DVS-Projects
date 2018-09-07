<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="Login-Register.css">

<title>Register</title>
</head>
<body>
<div id="id02">
  
  <form class="modal-content animate" action="http://localhost:8080/DVS-Projects/VerificationCode.jsp" method="post">
  

    <div class="container">
    
     <label for="fn"><b>First Name</b></label>
      <input type="text" placeholder="Enter First Name" name="Firstname" required>
      
       <label for="ln"><b>Last Name</b></label>
      <input type="text" placeholder="Enter Last Name" name="Lastname" required>
    
      <label for="uname"><b>Username</b></label>
      <input type="text" placeholder="Enter Username" name="EmailUsername" required>
      
  

      <label for="psw"><b>Password</b></label>
      <input type="password" id=password placeholder="Enter Password" name="Password" onkeyup='check();'  required><br><br>
	<input type="checkbox" onclick="myFunction()">Show Password</br></br>
      
       <label for="conpsw"><b>Confirm Password</b></label>
      <input type="password" id=Cpassword placeholder="Enter Password" name="Password" onkeyup='check();'  required><br><br>
	<input type="checkbox" onclick="myFunction1()">Show Password</br></br>
        <span id='message'></span><br>      
    <button type="submit"  id="submit" disabled>Register</button>
    </div>
  </form>
</div>
</body>
<script>
//Comparing password and confirm password and displaying message 
var check = function() {
  if (document.getElementById('password').value ==
    document.getElementById('Cpassword').value) {
   
    document.getElementById('message').style.color = 'green';
    document.getElementById('message').innerHTML = 'Passwords are matching';
     document.getElementById('submit').disabled = false;
  } else {
     
    document.getElementById('message').style.color = 'red';
    document.getElementById('message').innerHTML = 'Passwords are not matching';
     document.getElementById('submit').disabled = true;
  }
}

function myFunction() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function myFunction1() {
    var x = document.getElementById("Cpassword");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
</script>
</html>