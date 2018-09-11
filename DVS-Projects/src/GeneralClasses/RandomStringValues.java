package GeneralClasses;

import java.util.Random;

import javax.servlet.http.HttpSession;

public class RandomStringValues {
	public static String randomValues()
	{
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder combination = new StringBuilder();
		Random rnd = new Random();
		while (combination.length() < 18) 
		{ 
			int index = (int) (rnd.nextFloat() * characters.length());
			combination.append(characters.charAt(index));
		}
		String VerificationCode = combination.toString();
		//System.out.println(VerificationCode);
		
		return VerificationCode;
	}
}
