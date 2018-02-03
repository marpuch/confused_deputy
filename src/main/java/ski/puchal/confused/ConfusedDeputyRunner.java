package ski.puchal.confused;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfusedDeputyRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		CommandLineParser parser = new DefaultParser();
		
		Options options = createCliOptions();
		try {
			CommandLine cli = parser.parse(options, args);
			
			if (cli.hasOption("h")) {
				printHelp(options);
				System.exit(0);
			} else if (cli.hasOption("i") && cli.hasOption("o")) {
				
			} else {
				printHelp(options);
				System.exit(0);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println();
			printHelp(options);
		}
	}

	private Options createCliOptions() {
		Options options = new Options();
		options.addOption("i", "in", true, "input file, mandatory");
		options.addOption("o", "out", true, "output file, mandatory");
		options.addOption("h", "help", false, "prints this help");
		return options;
	}
	
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("java -jar [jar] --in=[file] --out=[file]", options);
	}

}
