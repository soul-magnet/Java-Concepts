package basics.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileExample2 {

	private static final String FILENAME = "C:\\zip5\\testForFile\\notExisiting.txt";

	public static void main(String[] args) {
		
/*		BufferedWriter bw = null;
		FileWriter fw = null;
			try {
				String content = "This is the content to write into file xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxYYYYYYYYYYYYYYYYYY\n";
				File file = new File(FILENAME);
				file.getParentFile().mkdirs();
				System.out.println(file.getParentFile().toString());
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				bw.write(content);
				System.out.println("Done");
				
			}catch(Exception e) {
				e.printStackTrace();				
			}*/

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME))) {

			String content = "This is the content to write into file xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n";

			bw.write(content);
			
			// no need to close it.
			//bw.close();

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
