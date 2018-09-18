package GeneralClasses;

import java.util.Random;

public class RandomStringValues 
{
	public static String randomValues()
	{
		String characters = "1234567890";
		StringBuilder combination = new StringBuilder();
		Random rnd = new Random();
		
		//Declare the length of the code and generate random number
		
		while (combination.length() < 7) 
		{ 
			int index = (int) (rnd.nextFloat() * characters.length());
			combination.append(characters.charAt(index));
		}
		String VerificationCode = combination.toString();
		
		//System.out.println(VerificationCode);
		
		return VerificationCode;
	}
}
