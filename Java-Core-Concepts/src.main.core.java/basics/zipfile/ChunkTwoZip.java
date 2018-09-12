package basics.zipfile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * The purpose of this code is to divide the large zip files into chunks - i found over the Internet
 * */

public class ChunkTwoZip {
	
	static final long MAX_LIMIT=10 * 1000 * 1024; //10MB limit - hopefully this
	
	public static void main(String[] args)  throws Exception {      

        String[] files = {"file1", "file2", "file3"};
        int i = 0;
        //boolean needNewFile = false;
        long overallSize = 0;
        ZipOutputStream out = getOutputStream(i);
        byte[] buffer = new byte[1024];

        for (String thisFileName: files) {
        	
        	if (overallSize > MAX_LIMIT) {
                    out.close();
                    i++;
                    out = getOutputStream(i);
                    overallSize=0;
                }

                FileInputStream in = new FileInputStream(thisFileName);
                ZipEntry ze = new ZipEntry(thisFileName);
                out.putNextEntry(ze);   
                int len;   
                while ((len = in.read(buffer)) > 0) {   
                    out.write(buffer, 0, len);   
                }   
                out.closeEntry();
                in.close(); 
                overallSize+=ze.getCompressedSize();

        }
        
        out.close();    
    }

    public static ZipOutputStream getOutputStream(int i) throws IOException {
    	
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream("bigfile" + i +".zip"));   
        out.setLevel(Deflater.DEFAULT_COMPRESSION);
        return out;
    }

}
