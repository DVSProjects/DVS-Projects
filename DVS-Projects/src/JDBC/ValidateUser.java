package JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidateUser 
{
	public static boolean validate(String UsernameRequestedToRegister)
	{
		DatabaseConnection.databaseConn();
		try
		{
			// Checking whether the requested username exists in the database or not
			
			PreparedStatement ps = DatabaseConnection.con.prepareStatement("select USERNAME from DVS where USERNAME=?");
			ps.setString(1, UsernameRequestedToRegister);
			ResultSet rs = ps.executeQuery();
			if(rs.next()==true)
			{
				return true;
			}
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
