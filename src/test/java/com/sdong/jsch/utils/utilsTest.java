package com.sdong.jsch.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sdong.jsch.config.RunParameters;

public class utilsTest {
	private static final Logger LOG = LoggerFactory.getLogger(utilsTest.class);

	@Test
	public void testCheckArgs_wrongParameters() {

		String[] args = { "-i", "input", "-x", "output" };
		RunParameters cmd = Utils.checkArgs(args);
		LOG.debug(cmd.toString());

	}

}
