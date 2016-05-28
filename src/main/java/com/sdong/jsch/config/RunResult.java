package com.sdong.jsch.config;

import net.neoremind.sshxcute.core.Result;

public class RunResult {
	private SerConfig serConfig;
	private Result result;

	public RunResult(SerConfig serConfig, Result result) {
		this.serConfig = serConfig;
		this.result = result;
	}

	public SerConfig getSerConfig() {
		return serConfig;
	}

	public void setSerConfig(SerConfig serConfig) {
		this.serConfig = serConfig;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	
}
