package User.Image.Insert.Update;

import JDBC.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.servlet.annotation.MultipartConfig;

@WebServlet("/ProfilePicture")
@MultipartConfig(maxFileSize = 16177215)
public class ProfilePicture extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException
	{
		//Database Connection
	
		//DatabaseConnection.databaseConn(); 
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement;
		ResultSet resultSet;

		String message1=null;
		String localURL=null;
		InputStream inputStream=null;
		
		HttpSession session = request.getSession();
		String LoggedInUser = (String) session.getAttribute("LoggedInUsername");
	

		Part filepart= request.getPart("ProfilePicture");
		
		//Image details
		
		if(filepart!=null)
		{
			System.out.println(filepart.getName());
			System.out.println(filepart.getSize());
			System.out.println(filepart.getContentType());
			
			inputStream=filepart.getInputStream();
		}
		
		try {		
			preparedStatement = DatabaseConnection.con.prepareStatement("UPDATE DVS SET PROFILEPICTURE = ? WHERE USERNAME=?");
				
			//Storing image in database
			
			if(inputStream!=null)
				{
					byte[] StoringImageInDatabase = getBytesFromInputStream(inputStream);
					preparedStatement.setBytes(1, StoringImageInDatabase);
					preparedStatement.setString(2,LoggedInUser);
				}
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next())
					System.out.println("File uploaded and saved into database");
				
				
				
				preparedStatement = DatabaseConnection.con.prepareStatement("Select * from DVS where USERNAME=?");
 	            
				preparedStatement.setString(1,LoggedInUser);
				
				resultSet=preparedStatement.executeQuery();
 	            
				//Storing image from database to created user folder in local system
				
 	            while(resultSet.next())
 	            {
 	            	Blob imageBlob = resultSet.getBlob(3);
 	            	
 	            	byte RetrievingImageInDatabase[]= imageBlob.getBytes(1,(int)imageBlob.length());
 	            	
 	            	// Folder path to store image
 	            	
 	            	localURL="C:\\git\\Users\\"+LoggedInUser+"\\"+LoggedInUser+".jpg";
 	            	System.out.println(localURL);
 	            	FileOutputStream fout= new FileOutputStream(localURL);
 	            	fout.write(RetrievingImageInDatabase);
 	            
 	           message1="Success";
 	            	
 	            }
 	            
			   RequestDispatcher rd=request.getRequestDispatcher("/ProfilePicChange.jsp"); 
 	           rd.forward(request,response);
	            
	            System.out.println(message1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
