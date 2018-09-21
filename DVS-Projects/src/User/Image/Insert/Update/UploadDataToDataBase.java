package User.Image.Insert.Update;

import java.io.ByteArrayOutputStream;
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

@WebServlet("/UploadDataToDataBase")
@MultipartConfig(maxFileSize = 16177215)
public class UploadDataToDataBase extends HttpServlet 
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
			String UploadingUsername;//=(String)session.getAttribute("LoggedInUsername");  
			UploadingUsername="Vinayaka";
			String UploadingStatus=request.getParameter("Status");
			System.out.println(UploadingStatus);
			
			
			System.out.println("UploadingUsername:"+UploadingUsername+"\nUploadingStatus:"+UploadingStatus);
			Part UploadingPicture = request.getPart("UploadingPicture");
			
			//Image details
		try 
		{
			if(UploadingPicture!=null)
			{
				System.out.println(UploadingPicture.getName());
				System.out.println(UploadingPicture.getSize());
				System.out.println(UploadingPicture.getContentType());
				
				inputStream=UploadingPicture.getInputStream();
			}
			//To get the count of rows inserted
			PreparedStatement CountQuery = DatabaseConnection.con.prepareStatement("select count (*) from PUBLICPHOTOS");
			ResultSet resultSet = CountQuery.executeQuery();
			while(resultSet.next())	
				CountOfRows=resultSet.getInt(1);
			CountOfRows++;
			System.out.println(CountOfRows);
			PreparedStatement preparedStatement = DatabaseConnection.con.prepareStatement("insert into PUBLICPHOTOS(USERNAME, STATUS, PHOTO) values(?,?,?)");
			
			//Storing image in database
			
			if(inputStream!=null)
				{
					byte[] StoringImageInDatabase = getBytesFromInputStream(inputStream);
					
					preparedStatement.setString(1,UploadingUsername);
					preparedStatement.setString(2, UploadingStatus);
					preparedStatement.setBytes(3, StoringImageInDatabase);
				}
			 resultSet = preparedStatement.executeQuery();
				if(resultSet.next())
					System.out.println("File and status uploaded and saved into database");
				
				//Inserting newly uploaded images to local folder
				
				preparedStatement = DatabaseConnection.con.prepareStatement("Select * from PUBLICPHOTOS where PICKSTATUS IS NULL");
				resultSet=preparedStatement.executeQuery();
				System.out.println("came through select");
				while(resultSet.next())
 	            {
 	            	Blob imageBlob = resultSet.getBlob(3);
 	            	
 	            	byte RetrievingImageInDatabase[]= imageBlob.getBytes(1,(int)imageBlob.length());
 	            	
 	            	// Folder path to store image
 	            	
 	            	localURLForPublicPhotos="C:\\git\\UploadedPictures\\"+CountOfRows+"."+UploadingStatus+".jpg";
 	            	System.out.println(localURLForPublicPhotos);
 	            	FileOutputStream fout= new FileOutputStream(localURLForPublicPhotos);
 	            	fout.write(RetrievingImageInDatabase);
 	            
 	            	message1="Success";
 	            	preparedStatement = DatabaseConnection.con.prepareStatement("update PUBLICPHOTOS SET PICKSTATUS = 'MOVED TO LOCAL SYSTEM' WHERE PICKSTATUS IS NULL");
 	            	
 	            	preparedStatement.executeQuery();
 	            	
 	           System.out.println(message1);
 	           
 	            }
					
			
			request.getRequestDispatcher("Socialized.jsp").forward(request,response);
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