package com.sdong.jsch;

import java.util.ArrayList;

import com.sdong.jsch.config.GetConfig;
import com.sdong.jsch.config.RunParameters;
import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;
import com.sdong.jsch.config.ServerSetting;
import com.sdong.jsch.exception.ConfigException;
import com.sdong.jsch.execute.ExecuteTask;
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
			ExecuteTask.ChangeDefatulSetting(serverSet.getDefaultSetting());

			ArrayList<SerConfig> serConfigList = serverSet.getSerConfig();

			// run task one by one
			for (SerConfig serConfig : serConfigList) {
				RunResult result = ExecuteTask.Execute(serConfig);
				resultList.add(result);
			}

			// output
			ExportResult.output(runParameters.getOutputFile(), resultList);
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
