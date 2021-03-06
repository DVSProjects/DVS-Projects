package Servlets.Login.Register;
import User.Image.Insert.Update.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.mail.smtp.SMTPAddressFailedException;

import JDBC.*;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet
{
	
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException 
	{
		DatabaseConnection.databaseConn();
	}
	public void destroy()
	{
		
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter Out = response.getWriter();    

		try
		{
		response.setContentType("text/html");
		//String UsernameToBeRegistered=request.getParameter("Username");  
		//String PasswordToBeRegistered=request.getParameter("Password"); 
		
		HttpSession session = request.getSession();
		String UsernameToBeRegistered = (String) session.getAttribute("RegisterUserEmail");
		String Password = (String) session.getAttribute("Password");
		String FirstName = (String) session.getAttribute("Firstname");
		String LastName = (String) session.getAttribute("Lastname");
	
		String ProfilePic = null;
		//Validating whether user is existing or not
		
		//boolean validate = ValidateUser.validate(UsernameToBeRegistered);
		//System.out.println(validate);
		
		//if(validate == true)
		//{
			PreparedStatement InsertData = DatabaseConnection.con.prepareStatement("insert into DVS(USERNAME, PASSWORD, PROFILEPICTURE, FIRSTNAME, LASTNAME) values(?,?,?,?,?)");   
			InsertData.setString(1,UsernameToBeRegistered);  
			InsertData.setString(2,Password);
			InsertData.setString(3, ProfilePic );
			InsertData.setString(4,FirstName);  
			InsertData.setString(5,LastName);  
			InsertData.executeUpdate(); 
			System.out.println("Data Inserted");
			
			//Create a folder for Registered user to store Profile picture
			
			CreateFolderForLoggedinUser.createFolder(UsernameToBeRegistered);
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		    rd.forward(request,response);  
		    
	//	}
	/*	else
		{
			System.out.println();
			Out.print("Username/Password already exists");
			Out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/NewUser.jsp>Go back</a>");
		}	*/
	
		}
		catch (SQLIntegrityConstraintViolationException s)
		{
			Out.print("Unable to load the page, please check the SQL query");
			Out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/NewUser.jsp>Go back</a>");
			s.printStackTrace();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
