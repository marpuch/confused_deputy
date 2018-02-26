package ski.puchal.confused;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class WorkLogic {

	private static final String WORKING_DIR_STRING = "./work/";

	private static final File WORKING_DIR = new File(WORKING_DIR_STRING);

	public void encodeFile(String inString, String outString) {
		createDirectory();
		
		try (BufferedReader in = new BufferedReader(new FileReader(inString));
			 // NOTE: what you see below is bad practice (path traversal, local file inclusion vulnerabilities)
			 //       Yet this is what the example is about
			 BufferedWriter out = new BufferedWriter(new FileWriter(WORKING_DIR_STRING + outString));) {
			Encoder encoder = Base64.getEncoder();
			in.lines()
			  .map(line -> encoder.encodeToString(line.getBytes()))
			  .forEach(line -> {
				try {
					out.write(line);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(0);
				}
			});;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void createDirectory() {
		if (!WORKING_DIR.exists()) {
			WORKING_DIR.mkdirs();
		}
	}

}
