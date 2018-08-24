package Servlets.Login.Register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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

	protected void service(HttpServletRequest x, HttpServletResponse y) throws ServletException, IOException
	{
		try
		{
		y.setContentType("text/html");
		PrintWriter out = y.getWriter();            
		String n=x.getParameter("Username");  
		String p=x.getParameter("Password"); 
		boolean validate = ValidateUser.validate(n);
		System.out.println(validate);
		
		if(validate == true)
		{
			PreparedStatement ps = DatabaseConnection.con.prepareStatement("insert into DVS(Username, Password) values(?,?)");   
			ps.setString(1,n);  
			ps.setString(2,p);
			ps.executeUpdate(); 
			System.out.println("Data Inserted");
			RequestDispatcher rd=x.getRequestDispatcher("Login.html");  
		    rd.forward(x,y);  
		}
		else{
			System.out.println();
			out.print("Username/Password already exists");
			out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/NewUser.html>Go back</a>");
		}
	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
