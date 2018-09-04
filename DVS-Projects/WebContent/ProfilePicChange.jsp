<%@page import="JDBC.*"
import="java.sql.*"
import="java.sql.PreparedStatement"%>
<%
DatabaseConnection.databaseConn();
HttpSession sessions = request.getSession();
String ProfileUsername = (String) session.getAttribute("LoggedInUsername");
String ProfilePicPath="C:\\git\\Users\\"+ProfileUsername+"\\"+ProfileUsername+".jpg";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.img-circle {
  border-radius: 50%;
}
</style>
<title>Company Name</title>
<link rel="stylesheet" type="text/css" href="/DVS-Projects/InitialCss.css" />
</head>
<body>

	<div class="topnav">
		<a class="active" href="/EmployeeDetails/index.jsp" >Home</a>
		<a href="/EmployeeDetails/LeaveEligibility.jsp">Socialized</a>
		<a href="/EmployeeDetails/LeaveForm.jsp">Country Nuisance</a>
		<a href="/EmployeeDetails/About.jsp">News</a>
		
		<div class="top">
			<a class='img-circle' href="HomePage.jsp"> <img class = "img-circle" src=<%=ProfilePicPath %> style="height:42px; width:42px;"></a>
			<a href="HomePage.jsp">Edit profile</a>		 
		</div>
	</div>	
	<div class="footer">
			Designed and Developed by:<br/>
			DVS<br/>
			All Rights Reserved 2017-18
		</div>
</body>
</html>
