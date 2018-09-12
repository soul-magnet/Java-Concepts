package basics.zipfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipFiles {
	
	private static void unzip(String zipFIlePath, String destDir) {
		
		File dir = new File(destDir);
		
		//create output directory if it doesn't exist
		if(!dir.exists()) dir.mkdirs();
		FileInputStream fis;
		//buffer for read and write data to file
		byte[] buffer = new byte[1024];
		
		try {
			
			fis = new FileInputStream(zipFIlePath);
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			
			while(ze != null) {
				
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				System.out.println("unzipping to "+newFile.getAbsolutePath());
				//create Directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				
				while((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				
				fos.close();
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			
			//close last zipEntry
			zis.closeEntry();
			zis.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		String zipFilePath = "/Users/Emine.Topkaya/test_zip/Test.zip";
		String destDir = "/Users/Emine.Topkaya/test_unzip";
		unzip(zipFilePath, destDir);
	}

}
