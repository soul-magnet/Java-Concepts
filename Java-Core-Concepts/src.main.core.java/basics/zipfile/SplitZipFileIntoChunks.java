package basics.zipfile;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 
 * Below code is my solution to split zip file in directory structure to chunks based on desired size. - WORKING SOULUTION
 * 
 * https://stackoverflow.com/questions/243992/how-to-split-a-huge-zip-file-into-multiple-volumes/51214186#51214186
*/

public class SplitZipFileIntoChunks {
	
	private final static long MAX_FILE_SIZE = 1000 * 1000 * 1024; //  around 1GB 
	private final static String zipCopyDest =  "C:\\zip2split\\copy";
	
	public static void splitZip(String zipFileName, String zippedPath, String coreId) throws IOException {
		
		System.out.println("process whole zip file..");
		FileInputStream fis  = new FileInputStream(zippedPath);
		ZipInputStream zipInputStream = new ZipInputStream(fis);
		ZipEntry entry = null;
		int currentChunkIndex = 0;
		//using just to get the uncompressed size of the zipentries
		long entrySize = 0;
		ZipFile zipFile = new ZipFile(zippedPath);
	    Enumeration enumeration = zipFile.entries();
	    
	  	String copDest = zipCopyDest + "\\" + coreId + "_" + currentChunkIndex +".zip";
		
		FileOutputStream fos = new FileOutputStream(new File(copDest));
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		ZipOutputStream zos = new ZipOutputStream(bos);
		long currentSize = 0; 
		
		try {
			
			while ((entry = zipInputStream.getNextEntry()) != null && enumeration.hasMoreElements()) {
				
				ZipEntry zipEntry = (ZipEntry) enumeration.nextElement();
				System.out.println(zipEntry.getName());
			    System.out.println(zipEntry.getSize());
			    entrySize = zipEntry.getSize();
			        
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				//long entrySize = entry.getCompressedSize();
				//entrySize = entry.getSize(); //gives -1
				
				if((currentSize + entrySize) > MAX_FILE_SIZE) {
					
					zos.close();
					//construct a new stream
					//zos = new ZipOutputStream(new FileOutputStream(new File(zippedPath, constructCurrentPartName(coreId))));
					currentChunkIndex++;
					zos = getOutputStream(currentChunkIndex, coreId);
			        currentSize = 0;

				}else {
					
					currentSize += entrySize;
					zos.putNextEntry(new ZipEntry(entry.getName()));
					byte[] buffer = new byte[8192];
					int length = 0;
					
					while ((length = zipInputStream.read(buffer)) > 0) {
						
						outputStream.write(buffer, 0, length);
					}
					
					byte[] unzippedFile = outputStream.toByteArray();
					zos.write(unzippedFile);
					unzippedFile = null;
					outputStream.close();
					zos.closeEntry();
				}
				//zos.close();
			}
		} finally {
			zos.close();
		}
	 
		
	}
	
	 public static ZipOutputStream getOutputStream(int i, String coreId) throws IOException {
		 
		 System.out.println("inside of getOutputStream()..");
         ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipCopyDest + "\\" + coreId + "_" +  i +".zip"));   
        // out.setLevel(Deflater.DEFAULT_COMPRESSION);
         
         return out;
     }
	 
	 
	 public static void displayIt(File node) throws IOException{
		 
		 System.out.println(node.getAbsolutePath());
		 
		/* if(node.isDirectory()){
			 String[] subNode = node.list();
			 for(String fileName: subNode){
				 displayIt(new File(node, fileName));
				 System.out.println("calling ");
			 }
		 }*/
		 
		 File[] files = node.listFiles();
		 
			for (File file : files) {
				
				/*if (file.isDirectory()) {
					System.out.print("directory:");
				} else {
					System.out.print("     file:");
					System.out.println("calling ");
				}*/
				
				System.out.println(file.getCanonicalPath());
			}
	 }
	 
	 
	public static void main(String args[]) throws IOException{
		
//		String zipFileName = "Large_files _for_testing.zip";
//		String zippedPath= "C:\\zip2split\\Large_files _for_testing.zip";
//		String coreId = "Large_files _for_testing";
//		splitZip(zipFileName, zippedPath, coreId);
		displayIt(new File(zipCopyDest));
		
	}
	
	

}
