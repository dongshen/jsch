package com.sdong.jsch;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

import net.neoremind.sshxcute.core.Logger;

/**
 * Hello world! I am AntMan, Let me help you!
 *
 */
public class AntMan {
	static Logger logger = Logger.getLogger();

	public static void main(String[] args) {

		try {
			// check args
			RunParameters runParameters = Utils.checkArgs(args);

			Run(runParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
