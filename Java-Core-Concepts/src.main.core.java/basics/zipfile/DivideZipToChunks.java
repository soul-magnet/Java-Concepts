package basics.zipfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * The purpose of this code is try to divide the large zip file into the chunks
 * */

public class DivideZipToChunks {
	
	public static final long MAX_LIMIT=10 * 1000 * 1024; //10MB limit - hopefully this 
	public static String path = "C:\\zip2split\\Large_files _for_testing";
	private final static String PART_POSTFIX = ".part.";
	private final static String FILE_EXTENSION = ".zip";
	private static int currentChunkIndex;
	private static long currentSize;
	private static String name = "Large_files _for_testing";
	
	
    public static void main(String args[])  throws Exception {      
    	
    	final ZipFile file = new ZipFile( "C:\\zip2split\\Large_files _for_testing.zip" );
    	int i = 0;
    	
    	//int currentChunkIndex;
    	 //ZipOutputStream zipOutputStream = getOutputStream(i);
    	 ZipOutputStream zipOutputStream = null;
    	try
    	{
    	    final Enumeration<? extends ZipEntry> entries = file.entries();
    	    long overallSize = 0;
    	   
    	    while ( entries.hasMoreElements() )
    	    {
    	        final ZipEntry entry = entries.nextElement();
    	        long entrySize = entry.getCompressedSize();
    	        System.out.println( entry.getName() );
    	        String eachFile = entry.getName();
    	        String newPath  = path + "\\" +  eachFile;
    	        System.out.println("newPath: " + newPath);
    	        
    	        if((currentSize + entrySize) > MAX_LIMIT) {
    	        	//zipOutputStream.close();
    	            //constructNewStream();
    	        	  zipOutputStream = new ZipOutputStream(new FileOutputStream(new File(path, constructCurrentPartName())));
    	              currentChunkIndex++;
    	              currentSize = 0;
    	        } else {
    	            currentSize += entrySize;
    	            zipOutputStream.putNextEntry(entry);
    	        }
    	        
	        //use entry input stream:
	        readInputStream( file.getInputStream( entry ) );
    	    }
    	}
    	finally
    	{
    	    file.close();
    	}

    }
//    private static void closeStream() throws IOException {
//        zipOutputStream.close();
//    }
    
    private static String constructCurrentPartName() {
        // This will give names is the form of <file_name>.part.0.zip, <file_name>.part.1.zip, etc.
        StringBuilder partNameBuilder = new StringBuilder(name);
        partNameBuilder.append(PART_POSTFIX);
        partNameBuilder.append(currentChunkIndex);
        partNameBuilder.append(FILE_EXTENSION);
        return partNameBuilder.toString();
    }
    
    private static int readInputStream( final InputStream is ) throws IOException {
	    final byte[] buf = new byte[ 8192 ];
	    int read = 0;
	    int cntRead;
	    while ( ( cntRead = is.read( buf, 0, buf.length ) ) >=0  )
	    {
	        read += cntRead;
	    }
	    return read;
	}

    public static ZipOutputStream getOutputStream(int i) throws IOException {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("bigfile" + i +".zip"));   
        out.setLevel(Deflater.DEFAULT_COMPRESSION);
        return out;
    }

}
