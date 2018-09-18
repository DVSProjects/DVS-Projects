
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
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet 
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
		try
		{
			PrintWriter Out = response.getWriter();  
			
			//Accepting Username and Password from UI
			
			String LoginUsername=request.getParameter("Username");  

			String LoginPassword=request.getParameter("Password"); 
	
			PreparedStatement SelectData = DatabaseConnection.con.prepareStatement("select USERNAME,PASSWORD from DVS where USERNAME=? and PASSWORD=?");
			SelectData.setString(1, LoginUsername);
			SelectData.setString(2, LoginPassword);
			ResultSet SelectedData = SelectData.executeQuery();
			
			//Checking whether Username and password are stored in DB successfully or not
			
			if(SelectedData.next()==true)
			{ 
			System.out.println("Welcome" );
			
			//Storing the value of Username and Firstname in a session
			
			HttpSession session = request.getSession();
		    session.setAttribute("LoggedInUsername",LoginUsername );
		   
			RequestDispatcher rd=request.getRequestDispatcher("ProfilePicChange.jsp");  
		    rd.forward(request,response); 
			
			
			}
			else
			{
				Out.print("Please enter a valid Username/Password");
				Out.print("</br></br><a href=file:///C:/git/DVS-Projects/DVS-Projects/WebContent/Login.jsp>Go back</a>");
				  
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Error");
			e.printStackTrace();
						 
			RequestDispatcher rd=request.getRequestDispatcher("Login.html");
			rd.forward(request,response);  
		}
	}
}
