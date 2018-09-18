<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
	<head>	
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Profile</title>
		<link rel="stylesheet" type="text/css" href="/DVS-Projects/InitialCss.css" />		
	</head>
	<body>	
		<div class="topnav">		
			<a href="/DVS-Projects/HomePage.jsp" >Home</a>
			<a href="http://localhost:8080/DVS-Projects/Socialized.jsp">Socialized</a>
			<a href="/DVS-Projects/CountryGeneralNews.jsp">Country Nuisance</a>
			<a class="active" href="/DVS-Projects/News.jsp">News</a>	
			
			<form action="http://localhost:8080/DVS-Projects/ProfilePicture" method="post" enctype="multipart/form-data">			
				<div class="top" >			 
					ProfilePicture<input type="file" name="ProfilePicture" size="5" />
					<input type="submit" value="save">
			 		<a href="http://localhost:8080/DVS-Projects/Logout" >Log Out</a>			
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