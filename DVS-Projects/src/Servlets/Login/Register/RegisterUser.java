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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String UsernameToBeRegistered=request.getParameter("Username");  
		String PasswordToBeRegistered=request.getParameter("Password"); 
		
		//Validating whether user is existing or not
		
		boolean validate = ValidateUser.validate(UsernameToBeRegistered);
		System.out.println(validate);
		
		if(validate == true)
		{
			PreparedStatement InsertData = DatabaseConnection.con.prepareStatement("insert into DVS(Username, Password) values(?,?)");   
			InsertData.setString(1,UsernameToBeRegistered);  
			InsertData.setString(2,PasswordToBeRegistered);
			InsertData.executeUpdate(); 
			System.out.println("Data Inserted");
			
			// Email Verification has to be called
			
			//Create a folder for Registered user to store Profile picture
			
			CreateFolderForLoggedinUser.createFolder(UsernameToBeRegistered);
			RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");  
		    rd.forward(request,response);  
		    
		}
		else
		{
			System.out.println();
			Out.print("Username/Password already exists");
			Out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/NewUser.jsp>Go back</a>");
		}
	
		}
		catch (SQLIntegrityConstraintViolationException s)
		{
			Out.print("Username/Password already exists");
			Out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/NewUser.jsp>Go back</a>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
