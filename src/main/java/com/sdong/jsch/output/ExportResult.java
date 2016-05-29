package com.sdong.jsch.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import com.sdong.jsch.config.RunResult;

import net.neoremind.sshxcute.core.Logger;

public class ExportResult {
	static Logger logger = Logger.getLogger();

	public static void output(String outputFile, ArrayList<RunResult> resultList) {

		try {

			//outputFile = "output" + File.separator + outputFile;

			String lineSeperator = System.getProperty("line.separator");
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));

			String outputLine = "";
			for (RunResult result : resultList) {
				if (result.getResult().isSuccess) {
					outputLine = result.getSerConfig().getServerName() + ":" + result.getResult().sysout;
				} else {
					outputLine = result.getSerConfig().getServerName() + ":" + "ERROR:" + result.getResult().error_msg
							+ lineSeperator;
				}
				writer.write(outputLine);
				logger.putMsg(Logger.INFO, "output:" + outputLine);
			}
			writer.close();
		} catch (IOException e) {
			logger.putMsg(Logger.ERROR, "Output result fail with the following exception: " + e);
		}
	}
}
