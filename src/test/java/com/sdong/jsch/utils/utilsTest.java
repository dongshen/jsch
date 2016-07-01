package com.sdong.jsch.utils;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sdong.jsch.config.RunParameters;

public class utilsTest {
	private static final Logger LOG = Logger.getLogger(utilsTest.class);

	@Test
	public void testCheckArgs_wrongParameters() {

		String[] args = { "-i", "input", "-x", "output" };
		RunParameters cmd = Utils.checkArgs(args);
		LOG.debug(cmd);

	}

}
