package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public static void main(String[] args)
	{
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loaded");
			String URL = "jdbc:oracle:thin:@dbsync-testing.cvglyvvn64dx.us-east-1.rds.amazonaws.com:1521:ORCL";
			String USER = "dbsync";
			String PASS = "Avankia1";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connection established");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
