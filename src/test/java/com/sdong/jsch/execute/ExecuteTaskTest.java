package com.sdong.jsch.execute;

import static org.junit.Assert.fail;

import com.sdong.jsch.config.DefaultSetting;
import com.sdong.jsch.exception.ConfigException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteTaskTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExecuteTaskTest.class);

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeDefatulSetting() {
		DefaultSetting defaultSetting = new DefaultSetting();
		defaultSetting.setInterval_time_between_tasks(50);
		defaultSetting.setTimeout(1000);
		try {
			JschSetting.ChangeDefatulSetting(defaultSetting);
		} catch (ConfigException e) {
			LOG.error(e.getMessage(), e);
		}
	}

}
