package ski.puchal.confused;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BillLogic {

	private static final String BILL_FILE_STRING = "bill.txt";

	public void addBillInfo(String path) {
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
