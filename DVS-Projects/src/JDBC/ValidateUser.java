package JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidateUser 
{
	public static boolean validate(String UsernameRequestedToRegister)
	{
		//DatabaseConnection.databaseConn();
		try
		{
			PreparedStatement ps = DatabaseConnection.con.prepareStatement("select USERNAME from DVS where USERNAME=?");
			ps.setString(1, UsernameRequestedToRegister);
			ResultSet rs = ps.executeQuery();
			if(rs.next()==true)
			{
				return false;
			}
		}		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
