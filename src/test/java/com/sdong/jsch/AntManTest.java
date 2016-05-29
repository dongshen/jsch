package com.sdong.jsch;

import org.junit.Test;

public class AntManTest {

	@Test
	public void testRun() {
		String inputFile = "config\\servers.properties";
		String outputFile = "output\\test.log";
		AntMan.Run(inputFile, outputFile);
	}

}
