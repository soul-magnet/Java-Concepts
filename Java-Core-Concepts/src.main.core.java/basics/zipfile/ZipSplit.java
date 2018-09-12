package basics.zipfile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipSplit {
	
	public static String createZIP(String directoryPath, String zipFileName, String filesToZip) {
		
        try {
        	
            final int BUFFER = 104857600; // 100MB
            final long MAX_ZIP_SIZE = 3221225472L; //3 GB
            long currentSize = 0;
            int zipSplitCount =0;
            String files[] = filesToZip.split(",");
            
            if (!directoryPath.endsWith("/")) {
            	
                directoryPath = directoryPath + "/";
            }
            
            byte fileRAW[] = new byte[BUFFER];
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(directoryPath + zipFileName.toUpperCase()));
            ZipEntry zipEntry;
            FileInputStream entryFile;
            
            for (String aFile : files) {
            	
                zipEntry = new ZipEntry(aFile);
                
                if (currentSize >= MAX_ZIP_SIZE)
                {
                    zipSplitCount ++;
                    //zipOut.closeEntry();
                    zipOut.close();
                    zipOut = new ZipOutputStream(new FileOutputStream(directoryPath + zipFileName.toLowerCase().replace(".zip", "_"+zipSplitCount+".zip").toUpperCase()));
                    currentSize = 0;
                }
                zipOut.putNextEntry(zipEntry);
                entryFile = new FileInputStream(directoryPath + aFile);

                int count;
                while ((count = entryFile.read(fileRAW, 0, BUFFER)) != -1) {
                    zipOut.write(fileRAW, 0, count);

                    //System.out.println("number of Bytes read = " + count);
                }
                
                entryFile.close();
                zipOut.closeEntry();
                currentSize += zipEntry.getCompressedSize();
            }

            zipOut.close();
            //System.out.println(directory + " -" + zipFileName + " -Number of Files = " + files.length);
        } catch (FileNotFoundException e) {
        	
            return "FileNotFoundException = " + e.getMessage();
        } catch (IOException e) {
        	
            return "IOException = " + e.getMessage();
        } catch (Exception e) {
        	
            return "Exception = " + e.getMessage();
        }

        return "1";
    }
	
	public static void main(String args[]){
		System.out.println("calling createZip method...");
		String directoryPath = "C:\\zip2split";
		String zipFileName = "MH_Special_Largefile_141423_4GB";
		//createZIP(directoryPath, zipFileName);
	}

}
