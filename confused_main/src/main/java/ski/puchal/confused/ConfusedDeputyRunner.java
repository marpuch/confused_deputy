package ski.puchal.confused;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class ConfusedDeputyRunner {

	private ConfusedDeputyLogic deputyLogic = new ConfusedDeputyLogic();
	
	public static void main(String[] args) {
		ConfusedDeputyRunner runner = new ConfusedDeputyRunner();
		runner.handleRequest(args);
	}

	private void handleRequest(String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		Options options = createCliOptions();
		try {
			CommandLine cli = parser.parse(options, args);
			
			if (cli.hasOption("h")) {
				printHelp(options);
				System.exit(0);
			} else if (cli.hasOption("i") && cli.hasOption("o")) {
				deputyLogic.run(cli.getOptionValue("i"), cli.getOptionValue("o"));
			} else {
				printHelp(options);
				System.exit(0);
			}
		} catch (Exception e) {
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
		formatter.printHelp("ConfusedDeputyRunner --in=[file] --out=[file]", options);
	}

}
