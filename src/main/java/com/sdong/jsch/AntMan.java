package com.sdong.jsch;

import java.io.File;
import java.util.ArrayList;

import com.sdong.jsch.config.GetConfig;
import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;
import com.sdong.jsch.config.ServerSetting;
import com.sdong.jsch.exception.ConfigException;
import com.sdong.jsch.execute.ExecuteTask;
import com.sdong.jsch.output.ExportResult;

import net.neoremind.sshxcute.core.Logger;

/**
 * Hello world! I am AntMan, Let me help you!
 *
 */
public class AntMan {
	static Logger logger = Logger.getLogger();

	public static void main(String[] args) {
		String inputFile = "";
		String outputFile = "";
		try {
			// check args
			checkArgs(args);

			if (args[0].equalsIgnoreCase("-i")) {
				inputFile = args[1];
				outputFile = args[3];
			} else {
				inputFile = args[3];
				outputFile = args[1];
			}

			Run(inputFile, outputFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Run(String inputFile, String outputFile) {
		try {
			// String outputFile = "check_db_size.log";

			ArrayList<RunResult> resultList = new ArrayList<RunResult>();
			
			ServerSetting serverSet = GetConfig.getServerConfig(inputFile);
			ExecuteTask.ChangeDefatulSetting(serverSet.getDefaultSetting());
			
			ArrayList<SerConfig> serConfigList = serverSet.getSerConfig();

			// run task one by one
			for (SerConfig serConfig : serConfigList) {
				RunResult result = ExecuteTask.Execute(serConfig);
				resultList.add(result);
			}

			// output
			ExportResult.output(outputFile, resultList);
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static boolean checkArgs(String[] args) {
		boolean bVerify = true;
		int iParameters = args.length;
		if (iParameters <= 0 || iParameters == 1 || iParameters != 4) {
			printHelp();
		}

		if ((args[0].equalsIgnoreCase("-o") && args[2].equalsIgnoreCase("-i") == true)
				|| (args[0].equalsIgnoreCase("-i") && args[2].equalsIgnoreCase("-o") == true)) {
			// verify input file
			if (args[0].equalsIgnoreCase("-i")) {
				File infile = new File(args[1]);
				if (!infile.exists()) {
					System.err.println("Please make sure the input file is exist.");
					System.exit(1);
				}
			} else if (args[2].equalsIgnoreCase("-i")) {
				File infile = new File(args[3]);
				if (!infile.exists()) {
					System.err.println("Please make sure the input file is exist.");
					System.exit(1);
				}
			}
		} else {
			printHelp();
		}

		return bVerify;

	}

	private static void printHelp() {
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
