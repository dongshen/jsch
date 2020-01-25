package com.sdong.jsch.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.exception.ConfigException;

import net.neoremind.sshxcute.core.IOptionName;

public class GetConfig {
	private static final Logger LOG = LoggerFactory.getLogger(GetConfig.class);

	public static ServerSetting getServerConfig(String inputFile) throws ConfigException {

		// String inputFle = "config\\servers.properties";
		ServerSetting serverSet = new ServerSetting();

		DefaultSetting defaultSetting = new DefaultSetting();
		ArrayList<SerConfig> ser = new ArrayList<SerConfig>();

		Properties serversProp = new Properties();
		try {
			serversProp.load(new FileReader(inputFile));

			for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
				String serverName = (String) serverProp.getKey();
				String serverInfo = (String) serverProp.getValue();
				if (serverName.equalsIgnoreCase(IOptionName.TIMEOUT)) {
					defaultSetting.setTimeout(Long.parseLong(serverInfo));
				} else if (serverName.equalsIgnoreCase(IOptionName.HALT_ON_FAILURE)) {
					defaultSetting.setHalt_on_Failure(Boolean.parseBoolean(serverInfo));
				} else if (serverName.equalsIgnoreCase(IOptionName.INTEVAL_TIME_BETWEEN_TASKS)) {
					defaultSetting.setInterval_time_between_tasks(Long.parseLong(serverInfo));
				} else if (serverName.equalsIgnoreCase(IOptionName.SSH_PORT_NUMBER)) {
					defaultSetting.setSsh_port_number(Integer.parseInt(serverInfo));
				} else {
					String[] serverDetail = serverInfo.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
					if (serverDetail == null || serverDetail.length != 4) {
						String error = "server:" + serverName + ":" + serverInfo + ",get setting error!";
						LOG.error(error);
						throw new ConfigException(error);
					}

					SerConfig serConfig = new SerConfig();
					serConfig.setServerName(serverName);
					serConfig.setServerIP(foramteStr(serverDetail[0]));
					serConfig.setUser(foramteStr(serverDetail[1]));
					serConfig.setPassword(foramteStr(serverDetail[2]));
					serConfig.setCommand(foramteStr(serverDetail[3]));
					ser.add(serConfig);
				}
			}
			serverSet.setDefaultSetting(defaultSetting);
			serverSet.setSerConfig(ser);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new ConfigException(e.getCause().toString());
		}
		return serverSet;
	}

	private static String foramteStr(String input) {
		String rtn = input.trim();
		int lng = rtn.length();
		if (lng >= 2 && rtn.subSequence(0, 1).equals("\"")) {
			if (rtn.subSequence(lng - 1, lng).equals("\"")) {
				rtn = rtn.substring(1, lng - 1);
			}
		}

		return rtn;

	}
}
