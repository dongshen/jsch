package com.sdong.jsch;

import org.junit.Test;

import com.sdong.jsch.config.RunParameters;

public class AntManTest {

	@Test
	public void testRun() {
		RunParameters runParameters = new RunParameters();
		runParameters.setInputFile("config\\servers.properties");
		runParameters.setOutputFile("output\\test.log");
		AntMan.Run(runParameters);
	}

}
