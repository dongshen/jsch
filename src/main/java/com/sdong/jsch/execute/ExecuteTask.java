package com.sdong.jsch.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class ExecuteTask {
	private static final Logger logger = LoggerFactory.getLogger(ExecuteTask.class);

	private static final String TASK_UPLOAD_FILE = "upload-file";
	private static final String TASK_UPLOAD_FOLDER = "upload-folder";

	private SerConfig serConfig;

	public ExecuteTask(SerConfig serConfig) {
		this.serConfig = serConfig;
	}

	public RunResult Execute() {
		SSHExec ssh = null;
		Result res = null;
		String command = null;
		try {
			SSHExec.showEnvConfig();

			ConnBean cb = new ConnBean(serConfig.getServerIP(), serConfig.getUser(), serConfig.getPassword());
			ssh = SSHExec.getInstance(cb);
			ssh.connect();

			command = serConfig.getCommand().trim();
			if (command.startsWith(TASK_UPLOAD_FILE)) {
				String[] parameters = command.split(" ");
				ssh.uploadSingleDataToServer(parameters[1], parameters[2]);
			} else if (command.startsWith(TASK_UPLOAD_FOLDER)) {
				String[] parameters = command.split(" ");
				ssh.uploadAllDataToServer(parameters[1], parameters[2]);
			} else {
				CustomTask echo = new ExecCommand(command);
				res = ssh.exec(echo);
			}
			if (res.isSuccess) {
				System.out.println("Return code: " + res.rc);
				System.out.println("sysout: " + res.sysout);
			} else {
				System.out.println("Return code: " + res.rc);
				System.out.println("error message: " + res.error_msg);
			}

		} catch (TaskExecFailException e) {
			logger.error("Run task fail with the following exception: ", e);
		} catch (Exception e) {
			logger.error("Run task fail with the following exception: ", e);
		} finally {
			if (ssh != null) {
				ssh.disconnect();
			}
		}

		return new RunResult(serConfig, res);
	}

	public SerConfig getSerConfig() {
		return serConfig;
	}

	public void setSerConfig(SerConfig serConfig) {
		this.serConfig = serConfig;
	}

}
