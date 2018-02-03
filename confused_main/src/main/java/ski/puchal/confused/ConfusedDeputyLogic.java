package ski.puchal.confused;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.stereotype.Component;

@Component
public class ConfusedDeputyLogic {

	private static final String WORKING_DIR_STRING = "./work/";
	private static final String BILL_FILE_STRING = "bill.txt";

	private static final File WORKING_DIR = new File(WORKING_DIR_STRING);

	public void run(String inString, String outString) {
		createDirectory();
		encodeFile(inString, outString);
		addBillInfo(outString);
	}

	private void addBillInfo(String path) {
		File file = new File(BILL_FILE_STRING);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String text = "File: " + path + " Price: 0.01$\n";
		try {
		    Files.write(Paths.get(BILL_FILE_STRING), 
		    		    text.getBytes(), 
		    		    StandardOpenOption.APPEND);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void encodeFile(String inString, String outString) {
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
