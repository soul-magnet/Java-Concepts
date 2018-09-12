package basics.file;

public class TrimFilePath {
	
	/**
	  * Trim the file path from the given file name. Anything before the last occurred "/" and "\" will be
	  * trimmed, including the slash.
	  * 
	  * @param fileName
	  *            The file name to trim the file path from.
	  * @return The file name with the file path trimmed.
	  */
	  public static String trimFilePath(String fileName) {
		  
	      return fileName.substring(fileName.lastIndexOf("/") + 1).substring(fileName.lastIndexOf("\\") + 1);
	  }
	  
	  public static void main(String args[]) {
		  
		  String fileName = "pic.pdf";
		  trimFilePath(fileName);
		  System.out.println("returns: " + trimFilePath(fileName));
	  }
	
	

}
