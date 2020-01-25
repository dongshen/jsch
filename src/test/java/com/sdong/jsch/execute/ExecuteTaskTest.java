package com.sdong.jsch.execute;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.sdong.jsch.config.DefaultSetting;
import com.sdong.jsch.exception.ConfigException;

public class ExecuteTaskTest {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
