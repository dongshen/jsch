package com.sdong.jsch;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.config.GetConfig;
import com.sdong.jsch.config.RunParameters;
import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;
import com.sdong.jsch.config.ServerSetting;
import com.sdong.jsch.exception.ConfigException;
import com.sdong.jsch.execute.ExecuteTask;
import com.sdong.jsch.execute.JschSetting;
import com.sdong.jsch.execute.WorkerThread;
import com.sdong.jsch.output.ExportResult;
import com.sdong.jsch.utils.Utils;

/**
 * Hello world! I am AntMan, Let me help you!
 *
 */
public class AntMan {
	private static final Logger logger = LoggerFactory.getLogger(AntMan.class);

	public static void main(String[] args) {

		try {
			// check args
			RunParameters runParameters = Utils.checkArgs(args);

			Run(runParameters);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public static void Run(RunParameters runParameters) {

		try {

			ArrayList<RunResult> resultList = new ArrayList<RunResult>();

			ServerSetting serverSet = GetConfig.getServerConfig(runParameters.getInputFile());
			JschSetting.ChangeDefatulSetting(serverSet.getDefaultSetting());

			ArrayList<SerConfig> serConfigList = serverSet.getSerConfig();

			// run task one by one
			for (SerConfig serConfig : serConfigList) {
				ExecuteTask task = new ExecuteTask(serConfig);
				RunResult result = task.Execute();
				resultList.add(result);
			}

			// output
			ExportResult.output(runParameters.getOutputFile(), resultList);
		} catch (ConfigException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static void RunWithThread(RunParameters runParameters) {

		try {
			// set thread num
			ExecutorService executor = Executors.newFixedThreadPool(runParameters.getThreadNum());

			ArrayList<RunResult> resultList = new ArrayList<RunResult>();

			// get command file
			ServerSetting serverSet;

			serverSet = GetConfig.getServerConfig(runParameters.getInputFile());
			ArrayList<SerConfig> serConfigList = serverSet.getSerConfig();

			// set jsch default value
			JschSetting.ChangeDefatulSetting(serverSet.getDefaultSetting());

			// run task one by one
			for (SerConfig serConfig : serConfigList) {
				Runnable worker = new WorkerThread(serConfig);
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			System.out.println("Finished all threads");
		} catch (ConfigException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
