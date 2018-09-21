<%@ page import ="java.io.*"%>
<%@ page import ="java.io.File.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import ="java.util.*"%>
<%@ page import ="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>
<%@ page import ="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>

<link rel="stylesheet" type="text/css" href="/DVS-Projects/InitialCss.css" />
</head>
<body class="backGround">

	<div class="topnav">
		<a href="http://localhost:8080/DVS-Projects/HomePage.jsp" >Home</a>
		<a href="http://localhost:8080/DVS-Projects/UploadDataToDataBase.jsp">Socialized</a>
		<a href="http://localhost:8080/DVS-Projects/UploadDataToDB_CountryGeneralNews.jsp">Country Nuisance</a>
		<a class="active" href="http://localhost:8080/DVS-Projects/UploadDataToDB_CountryGeneralNews.jsp">News</a>
		
	 	
	</div>	
	
	<form action="http://localhost:8080/DVS-Projects/UploadDataToDB_News" method="post" enctype="multipart/form-data">
			<div>
				Type what's the mood
				<br/>
				<input type="text" name="Status">
				<br/>
				
					What's on your mind
				<br/>
				<input type="file" name="UploadingPicture" size="5">
				<input type="submit" value="Upload">
			</div>
			
			
	</form>
	
	<%
				int num=0;
				List imageUrlList = new ArrayList();
				List imageDateList = new ArrayList();
				String imageDisplayed[]={};
				
				File imageDir = new File("C:/git/NEWSPHOTOS/");
				for(File imageFile : imageDir.listFiles()){
					
				  String imageFileName = imageFile.getName();
				  Long imageFileDate = imageFile.lastModified();
				  //out.println(imageFile.lastModified());
				  // add this images name to the list we are building up
				  imageUrlList.add(imageFileName);
				  imageDateList.add(imageFileDate);
				  
				 
				}
				
				request.setAttribute("imageUrlList", imageUrlList);
				request.setAttribute("imageDateList",imageDateList);
			%>
			
			
			 <% /*
			
				Collections.sort(imageDateList, Collections.reverseOrder() );
			for(Object i:imageDateList ){
				for(File j: imageDir.listFiles())
				{
					File imageDetails = j;
					if(i == (Object)j.lastModified()){
						imageDisplayed[num] = j.getName();
						out.println("this is displayed image===0"+imageDisplayed[num]);
					}
						
				}
				
				out.println(imageDisplayed);
			}
			 */
			%>
			 
			
			<%
			
			
			Collections.sort(imageUrlList, Collections.reverseOrder() );
			for(Object i:imageUrlList ) { 
			%> 
			<div style="max-width: 400px; padding-left: 30%;"  >
				<td>
					<%
					String s= (String)i ;
					String status[] = s.split("\\.");
					
					out.println(status[1]);
					%><br/> 
					<img src="C:/git/NEWSPHOTOS/<%=i %>" Style="float: center; width:100%;height:100%;"/> 
					
				<br/><br/><br/>
				
				</td> 
			</div>
			<% } %> 
			
	
	<div class="footer">
			Designed and Developed by:<br/>
			DVS<br/>
			All Rights Reserved 2017-18
		</div>
</body>
</html>