package com.sdong.jsch.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.exception.ConfigException;



public class GetConfigTest {

	private static final Logger LOG = LoggerFactory.getLogger(GetConfigTest.class);

	@Test
	public void testGetServerConfig() {
		try {
			String inputFile = "config\\servers.properties";
			ServerSetting serSet = GetConfig.getServerConfig(inputFile);
			LOG.debug(serSet.toString());
		} catch (ConfigException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
