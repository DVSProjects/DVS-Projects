package JDBC;
import java.sql.*;
public class DatabaseConnection 
{
	public static Connection con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	static String URL = "jdbc:oracle:thin:@dbsync-testing.cvglyvvn64dx.us-east-1.rds.amazonaws.com:1521:ORCL";		//Host name
	static String USERNAME = "dbsync";																				//Database Username
	static String PASSWORD = "Avankia1";																			// Database Password
	
	public static void databaseConn()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");  														//Loading Oracle Driver
			System.out.println("Driver Loaded");
					
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);												//Establishing connection
			System.out.println("Connection established");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
