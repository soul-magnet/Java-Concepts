package basics.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileToBytes {
	  public static void main(String[] args) {

	         File file = new File("c:/EventItemBroker.java");

	         byte[] b = new byte[(int) file.length()];
	         try {
	               FileInputStream fileInputStream = new FileInputStream(file);
	               fileInputStream.read(b);
	               for (int i = 0; i < b.length; i++) {
	                           System.out.print((char)b[i]);
	                }
	          } catch (FileNotFoundException e) {
	                      System.out.println("File Not Found.");
	                      e.printStackTrace();
	          }
	          catch (IOException e1) {
	                   System.out.println("Error Reading The File.");
	                    e1.printStackTrace();
	          }

	       }

}
