<%@page import="JDBC.*"
import="java.sql.*"
import="java.sql.PreparedStatement"%>
<%
	//DatabaseConnection.databaseConn();
	HttpSession sessions = request.getSession();
	String ProfileUsername = (String) session.getAttribute("LoggedInUsername");
	String ProfilePicPath="C:\\git\\Users\\"+ProfileUsername+"\\"+ProfileUsername+".jpg";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<style>
				.img-circle 
				{
 				 border-radius: 50%;
				}
			</style>
			<title>Company Name</title>
			<link rel="stylesheet" type="text/css" href="/DVS-Projects/InitialCss.css" />
	</head>
	<body>
		<div class="topnav">
		<a href="http://localhost:8080/DVS-Projects/HomePage.jsp" >Home</a>
		<a href="http://localhost:8080/DVS-Projects/UploadDataToDataBase.jsp">Socialized</a>
		<a href="http://localhost:8080/DVS-Projects/UploadDataToDB_CountryGeneralNews.jsp">Country Nuisance</a>
		<a class="active" href="http://localhost:8080/DVS-Projects/UploadDataToDB_CountryGeneralNews.jsp">News</a>
			<div class="top">
				<a class='img-circle' href="HomePage.jsp"> <img class = "img-circle" src=<%=ProfilePicPath %> style="height:42px; width:42px;"></a>
				<a href="HomePage.jsp">Edit profile</a>		 
				<a href="http://localhost:8080/DVS-Projects/Logout" >Log Out</a>		
			</div>
		</div>	
		<div class="footer">
			Designed and Developed by:<br/>
			DVS<br/>
			All Rights Reserved 2017-18
		</div>
	</body>
</html>
