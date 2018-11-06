package com.sdong.jsch.execute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.config.DefaultSetting;
import com.sdong.jsch.exception.ConfigException;

import net.neoremind.sshxcute.core.IOptionName;
import net.neoremind.sshxcute.core.SSHExec;

public class JschSetting {
	private static final Logger logger = LoggerFactory.getLogger(JschSetting.class);

	public static void ChangeDefatulSetting(DefaultSetting defaultSetting) throws ConfigException {
		SSHExec.setOption(IOptionName.HALT_ON_FAILURE, defaultSetting.isHalt_on_Failure());
		SSHExec.setOption(IOptionName.SSH_PORT_NUMBER, defaultSetting.getSsh_port_number());
		// SSHExec.setOption(IOptionName.ERROR_MSG_BUFFER_TEMP_FILE_PATH,
		// "c:\\123.err");
		SSHExec.setOption(IOptionName.INTEVAL_TIME_BETWEEN_TASKS, defaultSetting.getInterval_time_between_tasks());
		SSHExec.setOption(IOptionName.TIMEOUT, defaultSetting.getTimeout());
		try {
			SSHExec.showEnvConfig();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ConfigException(e);
		}
	}
}
