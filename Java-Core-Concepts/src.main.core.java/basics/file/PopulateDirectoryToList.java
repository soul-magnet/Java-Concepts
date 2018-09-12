package basics.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PopulateDirectoryToList {
	
	public static List<String> filesListInDir = new ArrayList<String>();
	
	public static void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getAbsolutePath());
	            filesListInDir.add(fileEntry.getAbsolutePath());
	        }
	    }
	   
	}
	
	public static void main(String args[]){
		final File folder = new File("C:\\zip5\\");
		listFilesForFolder(folder);
		
		if (filesListInDir != null && !filesListInDir.isEmpty()) {
			String lastItem = filesListInDir.get(filesListInDir.size()-1);
			System.out.println("last item in the zip file: " + lastItem);
			
			for(final String eachFile : filesListInDir){
				System.out.println("each File: " + eachFile);
			}
	}
	}

	

}
