package com.sdong.jsch.utils;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import com.sdong.jsch.config.RunParameters;

public class Utils {
	private static final Logger LOG = Logger.getLogger(Utils.class);

	public static RunParameters checkArgs(String[] args) {
		RunParameters runParameters = new RunParameters();

		Options options = new Options();
		// add t option
		// Option optInput = new Option("i", "input file for execute command
		// line");
		// Option optOutput = new Option("o", "result output file for execute
		// command line");

		options.addOption("i", true, "input file for execute command line");
		options.addOption("o", true, "result output file for execute command line");
		options.addOption("j", true, "the number of thread to run");

		// parse command
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;

		try {
			cmd = parser.parse(options, args);

			//check input
			if (!cmd.hasOption("i")) {
				System.err.println("-i <input file for execute command line> is required.");
				printHelp();
			} else {
				String inputFile = cmd.getOptionValue("i");
				File infile = new File(inputFile);
				if (!infile.exists()) {
					System.err.println("Please make sure the input file is exist.");
					System.exit(1);
				}
				runParameters.setInputFile(inputFile);
			}

			if (!cmd.hasOption("o")) {
				System.out.println("-o <result output file for execute command line> is required.");
			} else {
				runParameters.setOutputFile(cmd.getOptionValue("o"));
			}


		} catch (ParseException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
			System.err.println(e.getMessage());
			printHelp();
		}

		return runParameters;
	}

	public static void printHelp() {
		System.out.println("=============================");
		System.out.println("=      AntMan -- V1.0       =");
		System.out.println("= Create by Tom 2016.05.29  =");
		System.out.println("=============================");
		System.out.println(
				"AntMan will help you execute command based on Linux server list and return the output result to you.");
		System.out.println("-i <filename> -- input file which include server list and executed command");
		System.out.println("-o <filename> -- save executed command result");
		System.exit(1);
	}
}
