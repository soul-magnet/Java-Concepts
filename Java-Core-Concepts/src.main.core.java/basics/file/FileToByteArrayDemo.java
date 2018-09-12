package basics.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileToByteArrayDemo {

    public static void main(String[] args) {
        File file = new File("F:\\NetJS\\Articles.txt");
        
        // Using java.io.FileInputStream
        byte[] bArray = readFileToByteArray(file);
        for (int i = 0; i < bArray.length; i++){
           System.out.print((char) bArray[i]);
        }
        
        // Using ApacheCommons methods
        readToByteArrayUsingCommons(file);       
    }
    
    /**
     * This method uses java.io.FileInputStream to read
     * file content into a byte array
     * @param file
     * @return
     */
    private static byte[] readFileToByteArray(File file){
        FileInputStream fis = null;
        // Creating a byte array using the length of the file
        // file.length returns long which is cast to int
        byte[] bArray = new byte[(int) file.length()];
        try{
            fis = new FileInputStream(file);
            fis.read(bArray);
            fis.close();        
            
        }catch(IOException ioExp){
            ioExp.printStackTrace();
        }
        return bArray;
    }
    
    /**
     * This method uses apache commons to read
     * file content into a byte array
     * @param file
     */
    private static void readToByteArrayUsingCommons(File file){
        try(FileInputStream fis = new FileInputStream(file)) {
            // Using IOUtils method, it takes FileInputStream 
            // object as param
            byte[] bArray = IOUtils.toByteArray(fis);
            for (int i = 0; i < bArray.length; i++){
               System.out.print((char) bArray[i]);
            }
            // Using FileUtils method, it takes file object
            // as param
            bArray = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }  
    }
}