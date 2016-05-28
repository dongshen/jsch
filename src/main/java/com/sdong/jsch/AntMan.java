package com.sdong.jsch;

import java.util.ArrayList;

import com.sdong.jsch.config.GetConfig;
import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;
import com.sdong.jsch.exception.ConfigException;
import com.sdong.jsch.execute.ExecuteTask;
import com.sdong.jsch.output.ExportResult;

import net.neoremind.sshxcute.core.Logger;

/**
 * Hello world! I am AntMan
 *
 */
public class AntMan {
	static Logger logger = Logger.getLogger();

	public static void main(String[] args) {
		try {
			String outputFile = "check_db_size.log";
			
			ArrayList<RunResult> resultList = new ArrayList<RunResult>();
			ArrayList<SerConfig> serConfigList = GetConfig.getServerConfig();

			// run task one by one
			for (SerConfig serConfig : serConfigList) {
				RunResult result = ExecuteTask.Execute(serConfig);
				resultList.add(result);
			}
			
			//output 
			ExportResult.output(outputFile, resultList);
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
