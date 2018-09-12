package basics.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The purpose of this code is to copy directory - dound over the Internet
 * */
public class CreateDirTest {
	
	 public static void main(String[] args) {
		 
	        // Define a deep directory structures and create all the
	        // directories at once.
	        String directories = "C:/kodejava/x/y/z/c/v/b/n/j/k";
	        Path newPath = Paths.get(directories);
	        System.out.println("New Path" + newPath.toString());
	        
	        try {
	            Files.createDirectories(newPath);
	            System.out.println("Directories created");
	        } catch (IOException e) {
	            System.err.println("Cannot create directories - " + e);
	        }
	        
	        //File file = new File(directories);

	        // The mkdirs will create folder including any necessary
	        // but nonexistence parent directories. This method returns
	        // true if and only if the directory was created along with
	        // all necessary parent directories.
	        //boolean created = file.mkdirs();
	        
	    }

}
