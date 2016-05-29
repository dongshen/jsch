package com.sdong.jsch.execute;

import com.sdong.jsch.config.DefaultSetting;
import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.IOptionName;
import net.neoremind.sshxcute.core.Logger;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class ExecuteTask {
	static Logger logger = Logger.getLogger();

	public static RunResult Execute(SerConfig serConfig) {
		SSHExec ssh = null;
		Result res = null;
		try {
			SSHExec.showEnvConfig();

			ConnBean cb = new ConnBean(serConfig.getServerIP(), serConfig.getUser(), serConfig.getPassword());
			ssh = SSHExec.getInstance(cb);
			CustomTask echo = new ExecCommand(serConfig.getCommand());
			ssh.connect();
			res = ssh.exec(echo);
			if (res.isSuccess) {
				System.out.println("Return code: " + res.rc);
				System.out.println("sysout: " + res.sysout);
			} else {
				System.out.println("Return code: " + res.rc);
				System.out.println("error message: " + res.error_msg);
			}

		} catch (TaskExecFailException e) {
			logger.putMsg(Logger.ERROR, "Run task fail with the following exception: " + e);
		} catch (Exception e) {
			logger.putMsg(Logger.ERROR, "Run task fail with the following exception: " + e);
		} finally {
			if (ssh != null) {
				ssh.disconnect();
			}
		}

		return new RunResult(serConfig, res);
	}

	public static void ChangeDefatulSetting(DefaultSetting defaultSetting) {
		SSHExec.setOption(IOptionName.HALT_ON_FAILURE, defaultSetting.isHalt_on_Failure());
		SSHExec.setOption(IOptionName.SSH_PORT_NUMBER, defaultSetting.getSsh_port_number());
		// SSHExec.setOption(IOptionName.ERROR_MSG_BUFFER_TEMP_FILE_PATH,
		// "c:\\123.err");
		SSHExec.setOption(IOptionName.INTEVAL_TIME_BETWEEN_TASKS, defaultSetting.getInterval_time_between_tasks());
		SSHExec.setOption(IOptionName.TIMEOUT, defaultSetting.getTimeout());
		try {
			SSHExec.showEnvConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
