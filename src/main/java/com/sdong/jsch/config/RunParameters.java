package com.sdong.jsch.config;

public class RunParameters {
	private String inputFile = "";
	private String outputFile = "";
	private int threadNum = 1;

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	@Override
	public String toString() {
		return inputFile + ", " + outputFile + ", " + threadNum;
	}

}
