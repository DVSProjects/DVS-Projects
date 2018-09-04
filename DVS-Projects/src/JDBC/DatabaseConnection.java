package JDBC;
import java.sql.*;
public class DatabaseConnection 
{
	public static Connection con;
	public static PreparedStatement ps;
	public static ResultSet rs;
	static String URL = "jdbc:oracle:thin:@dbsync-testing.cvglyvvn64dx.us-east-1.rds.amazonaws.com:1521:ORCL";		//JDBC Connection
	static String USERNAME = "dbsync"; //Database Username
	static String PASSWORD = "Avankia1";	// Database Password
	public static void databaseConn()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");  //Loading Oracle Driver
			System.out.println("Driver Loaded");
					
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
			System.out.println("Connection established");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/*Method to fetch the logged in username from the database which intern returns the path of the user's profile picture
	public static String DataFromDB(String User) 
	{
		try 
		{
			ps = con.prepareStatement("Select * from DVS where USERNAME=?");
			ps.setString(1,User);
			rs = ps.executeQuery();
			String Username=null;
			while(rs.next())
			{
			Username=rs.getString(1);
			}
	
			String UsernamePath="D:\\"+Username+".jpg" ;
			System.out.println(UsernamePath);
			return UsernamePath;

		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}*/
}
