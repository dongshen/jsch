package com.sdong.jsch.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sdong.jsch.exception.ConfigException;

public class GetConfig {
	private static final Logger LOG = Logger.getLogger(GetConfig.class);

	public static ArrayList<SerConfig> getServerConfig() throws ConfigException {
		ArrayList<SerConfig> ser = new ArrayList<SerConfig>();

		Properties serversProp = new Properties();
		try {
			serversProp.load(new FileReader("config\\servers.properties"));
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new ConfigException(e.getCause().toString());
		}

		for (Map.Entry<Object, Object> serverProp : serversProp.entrySet()) {
			String serverName = (String) serverProp.getKey();
			String serverInfo = (String) serverProp.getValue();
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
		return ser;
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
