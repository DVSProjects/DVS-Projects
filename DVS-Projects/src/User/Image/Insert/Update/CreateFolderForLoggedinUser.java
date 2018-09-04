package User.Image.Insert.Update;

import java.io.File;

public class CreateFolderForLoggedinUser 
{
	public static void createFolder(String CreateFolderForUsername)
	{
		
		String Folderpath = "C:\\git\\Users";	
		
	    File dir = new File(Folderpath+"/"+CreateFolderForUsername);
	    
	     if(dir.exists())
	     {
	         System.out.println("A folder with name"+CreateFolderForUsername+" is already exist in the path "+Folderpath);
	     }
	     else
	     {
	         dir.mkdir();	           
	     }
	}
}

