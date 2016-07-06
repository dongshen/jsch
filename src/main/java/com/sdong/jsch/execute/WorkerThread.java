package com.sdong.jsch.execute;

import com.sdong.jsch.config.RunResult;
import com.sdong.jsch.config.SerConfig;

public class WorkerThread implements Runnable {

	private SerConfig serConfig;
	private RunResult result;
	
	public WorkerThread(SerConfig serConfig){
	        this.serConfig=serConfig;
	    }

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start.");
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End.");
	}

	private void processCommand() {
		ExecuteTask task = new ExecuteTask(serConfig);
		result = task.Execute();
	}

	@Override
	public String toString() {
		return this.serConfig.toString();
	}

}
