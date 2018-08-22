
package Servlets.Login.Register;
import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
{
	Connection con;
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
			PrintWriter out = y.getWriter();            
			String n=x.getParameter("Username");  
			System.out.println(n);
			String p=x.getParameter("Password"); 
			System.out.println(p);
			PreparedStatement ps = DatabaseConnection.con.prepareStatement("select * from DVS where USERNAME=? and PASSWORD=?");
			ps.setString(1, n);
			ps.setString(2, p);
			ResultSet rs = ps.executeQuery();
			if(rs.next()==true){
			System.out.println("Welcome");
			out.println("Welcome");
			}
			else
			{
				out.print("Please enter a valid Username/Password");
				out.print("</br></br><a href=file:///E:/Git/DVS-Projects/DVS-Projects/WebContent/Login.html>Go back</a>");
				  
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Error");
			 RequestDispatcher rd=x.getRequestDispatcher("Login.html");  
		     rd.forward(x,y);  
		}
	}
}
