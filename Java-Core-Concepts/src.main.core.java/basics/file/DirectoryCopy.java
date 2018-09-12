package basics.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Copy Directory example
 * */
public class DirectoryCopy {
	/*public static void main(String args[]){
		// An existing directory to copy.
        String source = "C:/Demo/source";
        File srcDir = new File(source);

        // The destination directory to copy to. This directory
        // doesn't exists and will be created during the copy
        // directory process.
        String destination = "C:/Demo/target";
        File destDir = new File(destination);

        try {
            // Copy source directory into destination directory
            // including its child directories and files. When
            // the destination directory is not exists it will
            // be created. This copy process also preserve the
            // date information of the file.
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /////////////////////////////
        if( response.getStatusLine().getStatusCode() == 200 ) {
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            try {
                sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( content ), 8 );
                String line;
                while( ( line = bufferedReader.readLine() ) != null ) {
                    sb.append( line );
                }
                bufferedReader.close();
                content.close();
            } catch( Exception ex ) {
                Log.e( "statusCode", ex.getMessage() + "" );
            }
        }
        
        
		
	}
	*/
	
	 public static void main(String[] args) throws IOException {
		 
		    String logPath = "C:\\zip2\\200002_dev_S1_Emily5_20_10_20.zip";
		    String safeFolder = "C:\\zip2\\temp\\";
		    ArrayList<File> files = new ArrayList<File>();
		    BufferedReader br = new BufferedReader(new FileReader(logPath));
		    String line = null;


		    while ((line = br.readLine()) != null) {
		            files.add(new File(line));

		            //System.out.println("File found:" + files.get(files.size() - 1));
		    }

		    String root = "C:\\zip2\\" ;

		    for (File f : files) {

		        if (f.exists()) {
		            File destFile = new File(safeFolder, f.getAbsolutePath().replace(root,""));
		            destFile.getParentFile().mkdirs();
		            if (!f.renameTo(destFile)) {
		                //System.out.println("Moving file: " + f + " to " + safeFolder);
		                //System.err.println("Unable to move file: " + f);
		            }
		       } else {
		            //System.out.println("Could not find file: " + f);
		        }
		    }
		}

}
