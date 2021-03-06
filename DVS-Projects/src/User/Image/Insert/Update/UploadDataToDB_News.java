package User.Image.Insert.Update;

import java.util.List;
import java.beans.Statement;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import JDBC.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/UploadDataToDB_News")
@MultipartConfig(maxFileSize = 16177215)
public class UploadDataToDB_News extends HttpServlet 
{
	Connection con;
	private static final long serialVersionUID = 1L;	
	public void init(ServletConfig config) throws ServletException
	{
		//JDBC Connectivity
		DatabaseConnection.databaseConn(); 
	}
	public void destroy()
	{
		
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		InputStream inputStream=null;
		int CountOfRows=0;
		String message1=null;
		String localURLForPublicPhotos=null;
		String oderNumberFromDB ;
		
			PrintWriter Out = response.getWriter();  
			//Accepting Status and Picture from UI
			HttpSession session = request.getSession();
			String UploadingUsernameForNews;//=(String)session.getAttribute("LoggedInUsername");  
			UploadingUsernameForNews="Vinayaka";
			String UploadingStatusForNews=request.getParameter("Status");
			System.out.println(UploadingStatusForNews);
			
			
			System.out.println("UploadingUsername:"+UploadingUsernameForNews+"\nUploadingStatus:"+UploadingStatusForNews);
			Part UploadingPictureForNews = request.getPart("UploadingPicture");
			
			//Image details
		try 
		{
			if(UploadingPictureForNews!=null)
			{
				System.out.println(UploadingPictureForNews.getName());
				System.out.println(UploadingPictureForNews.getSize());
				System.out.println(UploadingPictureForNews.getContentType());
				
				inputStream=UploadingPictureForNews.getInputStream();
			}
			//To get the count of rows inserted
			PreparedStatement CountQuery = DatabaseConnection.con.prepareStatement("select count (*) from NEWSPHOTOS");
			ResultSet resultSet = CountQuery.executeQuery();
			while(resultSet.next())	
				CountOfRows=resultSet.getInt(1);
			CountOfRows++;
			System.out.println(CountOfRows);
			PreparedStatement preparedStatement = DatabaseConnection.con.prepareStatement("insert into NEWSPHOTOS(USERNAME, STATUS, PHOTO) values(?,?,?)");
			
			//Storing image in database
			
			if(inputStream!=null)
				{
					byte[] StoringImageInDatabaseForNews = getBytesFromInputStream(inputStream);
					
					preparedStatement.setString(1,UploadingUsernameForNews);
					preparedStatement.setString(2, UploadingStatusForNews);
					preparedStatement.setBytes(3, StoringImageInDatabaseForNews);
				}
			 resultSet = preparedStatement.executeQuery();
				if(resultSet.next())
					System.out.println("File and status uploaded and saved into database");
				
				//Inserting newly uploaded images to local folder
				
				preparedStatement = DatabaseConnection.con.prepareStatement("Select * from NEWSPHOTOS where PICKSTATUS IS NULL");
				resultSet=preparedStatement.executeQuery();
				System.out.println("came through select");
				while(resultSet.next())
 	            {
 	            	Blob imageBlob = resultSet.getBlob(3);
 	            	
 	            	byte RetrievingImageInDatabase[]= imageBlob.getBytes(1,(int)imageBlob.length());
 	            	
 	            	// Folder path to store image
 	            	
 	            	localURLForPublicPhotos="C:\\git\\NEWSPHOTOS\\"+CountOfRows+"."+UploadingStatusForNews+".jpg";
 	            	System.out.println(localURLForPublicPhotos);
 	            	FileOutputStream fout= new FileOutputStream(localURLForPublicPhotos);
 	            	fout.write(RetrievingImageInDatabase);
 	            
 	            	message1="Success";
 	            	preparedStatement = DatabaseConnection.con.prepareStatement("update NEWSPHOTOS SET PICKSTATUS = 'MOVED TO LOCAL SYSTEM' WHERE PICKSTATUS IS NULL");
 	            	
 	            	preparedStatement.executeQuery();
 	            	
 	           System.out.println(message1);
 	           
 	            }
					
			
			request.getRequestDispatcher("News.jsp").forward(request,response);
		}
		catch(Exception e)
		{
			System.out.println("Error");
			e.printStackTrace();
						 
			RequestDispatcher rd=request.getRequestDispatcher("Login.html");
			rd.forward(request,response);  
		}
	}
	
	//Convertion of file to bytes
	
		public static byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
		    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 
		    byte[] buffer = new byte[0xFFFF];
		    for (int length = inputStream.read(buffer); length != -1; length = inputStream.read(buffer)) { 
		    	outputStream.write(buffer, 0, length);
		    }
		    return outputStream.toByteArray();
		}
	
}