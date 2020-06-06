package com.sdong.jsch.utils;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.config.RunParameters;

public class Utils {
	private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

	public static RunParameters checkArgs(String[] args) {
		RunParameters runParameters = new RunParameters();

		Options options = new Options();
		options.addOption("i", true, "input file for execute command line");
		options.addOption("o", true, "result output file for execute command line");
		options.addOption("j", true, "the number of thread to run");

		// parse command
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

			if (cmd != null) {
				// check input
				if (!cmd.hasOption("i")) {
					LOG.info("-i <input file for execute command line> is required.");
					printHelp();
				} else {
					String inputFile = cmd.getOptionValue("i");
					File infile = new File(inputFile);
					if (!infile.exists()) {
						LOG.info("Please make sure the input file is exist.");
						System.exit(1);
					}
					runParameters.setInputFile(inputFile);
				}

				// check output
				if (!cmd.hasOption("o")) {
					LOG.info("-o <result output file for execute command line> is required.");
				} else {
					runParameters.setOutputFile(cmd.getOptionValue("o"));
				}

				if (cmd.hasOption("j")) {
					String threadnum = cmd.getOptionValue("j");
					try {
						int num = Integer.parseInt(threadnum);
						if(num <= 0){
							LOG.info("Wrong value for parameter -j");
							printHelp();
						}
						runParameters.setThreadNum(num);
					} catch (NumberFormatException e) {
						LOG.info("Wrong value for parameter -j");
						printHelp();
					}
				}
			} else {
				printHelp();
			}

		} catch (ParseException e) {
			LOG.error(e.getMessage());
			printHelp();
		}

		return runParameters;
	}

	public static void printHelp() {
		LOG.info("=============================");
		LOG.info("=      AntMan -- V1.0       =");
		LOG.info("= Create by Tom 2016.05.29  =");
		LOG.info("=============================");
		LOG.info(
				"AntMan will help you execute command based on Linux server list and return the output result to you.");
		LOG.info("-i <filename> -- input file which include server list and executed command");
		LOG.info("-o <filename> -- save executed command result");
		LOG.info("-j <threadnum> -- the number of run thread, is option,default is 1");
		System.exit(1);
	}
}
