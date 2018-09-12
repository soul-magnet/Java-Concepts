package basics.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RemoveFileInfoFromPath {
	/**
	   * Remove file information from a filename returning only its path component
	   * 
	   * @param filename
	   *            The filename
	   * @return The path information

	   */
	  public static String pathComponent(String filename) {
	      int i = filename.lastIndexOf(File.separator);
	      return (i > -1) ? filename.substring(0, i) : filename;
	  }
	  
	  public static void main(String[] args){
		  System.out.println("returnin gonly path by removing file name");
		  String fileName="C:\\zip2\\temp\\manifest.xml";
		  //pathComponent(fileName);
		  System.out.println("The path is: " + pathComponent(fileName));
		  
		  String currentPath = pathComponent(fileName);
		  System.out.println("currentPath: " + currentPath);
		  Path newPath = Paths.get(currentPath, "manifest.xml");
		  System.out.println(newPath);
		  
		  try{
	    		
	    	    File temp = File.createTempFile("i-am-a-temp-file", ".tmp" );
	        	
	    	    String absolutePath = temp.getAbsolutePath();
	    	    System.out.println("File path : " + absolutePath);
	    	    
	    	    String filePath = absolutePath.
	    	    	     substring(2,absolutePath.lastIndexOf(File.separator));
					
	    	    System.out.println("File path : " + filePath);
	    	    
	    	    Path newPath2 = Paths.get(currentPath, filePath);
	    	    System.out.println("newPath2: " + newPath2);
	    	    System.out.format("subpath(1,3): %s%n", newPath2.subpath(1,3));
	    	    
	    	}catch(IOException e){
	    		
	    	    e.printStackTrace();
	    		
	    	}
	  }

}
