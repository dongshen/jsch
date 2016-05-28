package com.sdong.jsch.config;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sdong.jsch.exception.ConfigException;

public class GetConfigTest {

	private static final Logger LOG = Logger.getLogger(GetConfigTest.class);

	@Test
	public void testGetServerConfig() {
		try {
			ArrayList<SerConfig> serSet = GetConfig.getServerConfig();
			LOG.debug(serSet.toString());
		} catch (ConfigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
