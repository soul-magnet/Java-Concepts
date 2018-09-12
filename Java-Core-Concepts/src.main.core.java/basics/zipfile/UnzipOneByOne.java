package basics.zipfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipOneByOne {
	/*public static void getZipFiles(String filename) {
	    try {
	        String destinationname = "C:\\zip2\\temp\\";
	        byte[] buf = new byte[1024];
	        ZipInputStream zipinputstream = null;
	        ZipEntry zipentry;
	        zipinputstream = new ZipInputStream(
	                new FileInputStream(filename));

	        zipentry = zipinputstream.getNextEntry();
	        while (zipentry != null) {
	            //for each entry to be extracted
	            String entryName = destinationname + zipentry.getName();
	            entryName = entryName.replace('/', File.separatorChar);
	            entryName = entryName.replace('\\', File.separatorChar);
	            System.out.println("entryname " + entryName);
	            int n;
	            FileOutputStream fileoutputstream;
	            File newFile = new File(entryName);
	            if (zipentry.isDirectory()) {
	                if (!newFile.mkdirs()) {
	                    break;
	                }
	                zipentry = zipinputstream.getNextEntry();
	                continue;
	            }
	            
	            fileoutputstream = new FileOutputStream(entryName);

	            while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
	                fileoutputstream.write(buf, 0, n);
	            }

	            fileoutputstream.close();
	            zipinputstream.closeEntry();
	            zipentry = zipinputstream.getNextEntry();

	        }//while

	        zipinputstream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}*/
	
	public static void copyFolder(File source, File destination)
	{
	    if (source.isDirectory())
	    {
	        if (!destination.exists())
	        {
	            destination.mkdirs();
	        }

	        String files[] = source.list();

	        for (String file : files)
	        {
	            File srcFile = new File(source, file);
	            File destFile = new File(destination, file);

	            copyFolder(srcFile, destFile);
	        }
	    }
	    else
	    {
	        InputStream in = null;
	        OutputStream out = null;

	        try
	        {
	            in = new FileInputStream(source);
	            out = new FileOutputStream(destination);

	            byte[] buffer = new byte[1024];

	            int length;
	            while ((length = in.read(buffer)) > 0)
	            {
	                out.write(buffer, 0, length);
	            }
	        }
	        catch (Exception e)
	        {
	            try
	            {
	                in.close();
	            }
	            catch (IOException e1)
	            {
	                e1.printStackTrace();
	            }

	            try
	            {
	                out.close();
	            }
	            catch (IOException e1)
	            {
	                e1.printStackTrace();
	            }
	        }
	    }
	}
	
	
	public static void main(String[] args){
		
		System.out.println("Calling unzip method");
		String filePath = "c:\\zip2\\200002_dev_S1_Emily5_20_10_20.zip";
		//getZipFiles(filePath);
		String source= "c:\\zip2\\200002_dev_S1_Emily5_20_10_20.zip";
		String destination = "C:\\zip2\\temp\\";
		
		copyFolder(source, destination);
		
	}
	

}
