<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" type="text/css" href="/DVS-Projects/InitialCss.css" />
</head>
<body>
	<div class="topnav">
		<a class="active" href="/EmployeeDetails/index.jsp" >Home</a>
		<a href="/EmployeeDetails/LeaveEligibility.jsp">Socialized</a>
		<a href="/EmployeeDetails/LeaveForm.jsp">Country Nuisance</a>
		<a href="/EmployeeDetails/About.jsp">News</a>
	
	<form action="http://localhost:8080/DVS-Projects/ProfilePicture" method="post" enctype="multipart/form-data">
		<div class="top" >
			 
			ProfilePicture<input type="file" name="ProfilePicture" size="5" />
			<input type="submit" value="save">
			 
			
		</div>
		
			
		<div class="footer">
			Designed and Developed by:<br/>
			DVS<br/>
			All Rights Reserved 2017-18
		</div>
	</form>
	</div>
</body>

</html>