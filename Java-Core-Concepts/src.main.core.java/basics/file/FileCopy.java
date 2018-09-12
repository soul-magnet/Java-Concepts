package basics.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;


public class FileCopy {
	
	public static void copyFiles(String fileLocationSource, String fileLocationDestionation, int numberOfFilesToCopy){
		File inputLocation = new File(fileLocationSource);
		if(inputLocation.isDirectory()){
			System.out.println("Listing the files...");
			File[] attachmentFiles = inputLocation.listFiles();
			System.out.println("Total files in the folder: " + attachmentFiles.length);
			for (File aFile : attachmentFiles){
				if(!aFile.isDirectory()){
					String fileName = aFile.getName();
					String sourceFileName = aFile.getAbsolutePath();
					String destinationFileName = fileLocationDestionation + fileName;
					copyFile(sourceFileName, destinationFileName);
				}
				if(numberOfFilesToCopy >= 0){
					if(--numberOfFilesToCopy == 0)
						break;
				}
			}
			System.out.println("Completed...");
			
		}
	}
	
	/**
	 * @param sourceFileName
	 * @param destinationFileName
	 * 
	 * Copies a single file from source location to a destination location
	 * */
	private static void copyFile(String sourceFileName, String destinationFileName) {
		try {
			System.out.println("Reading..." + sourceFileName );
			File sourceFile = new File(sourceFileName);
			File destionationFile = new File(destinationFileName);
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(destionationFile);
			
			byte[] buffer = new byte[1024];
			int length;
			while((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			System.out.println("Copied... + " + sourceFileName);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private static void copyZipFile(File zipFile, File newFile) throws IOException {
	    ZipFile zipSrc = new ZipFile(zipFile);
	    ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(newFile));

	    Enumeration srcEntries = zipSrc.entries();
	    while (srcEntries.hasMoreElements()) {
	            ZipEntry entry = (ZipEntry) srcEntries.nextElement();
	            ZipEntry newEntry = new ZipEntry(entry.getName());
	            zos.putNextEntry(newEntry);

	            BufferedInputStream bis = new BufferedInputStream(zipSrc
	                            .getInputStream(entry));

	            while (bis.available() > 0) {
	                    zos.write(bis.read());
	            }
	            zos.closeEntry();

	            bis.close();
	    }
	    zos.finish();
	    zos.close();
	    zipSrc.close();
	 }
	
	private static void copyZip2File(File zipFile, File newFile) throws IOException {
		
		
		
	}
	
	public static void main(String[] args) throws IOException {
		String fileLocationSourceNetwork = "C:\\receiptDir\\Devunzip_tmp\\E.Topkaya\\";
		
		String fileLocationDestination = "C:\\receiptDir\\Devunzip_tmp\\temp_\\";
		int numberOfFilesToCopy = 6;
		/**Copy the files from the network**/
		/*copyFiles(  fileLocationSourceNetwork,
                fileLocationDestination,
                numberOfFilesToCopy);
		System.out.println("nn");*/
		/**Copy the files from the drive**/
        /*copyFiles(  fileLocationSourceDrive,
                fileLocationDestination,
                numberOfFilesToCopy);*/
		
		String source = "C:\\zip5\\141180_dev_after_change_06_01_28.zip";
		File newSource = new File(source);
		
		String destination = "/cvs004/Apps/CVM_Apps/Test/Electronic_Submissions/eDERS/subFiles/zippedNew.zip";
		File newDestination = new File(destination);
		copyZipFile(newDestination, newSource);
		
		
	}
	
	
}
