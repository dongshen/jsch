package com.sdong.jsch.config;

import java.io.File;

public class DefaultSetting {
	private long timeout = 100000;

	private boolean halt_on_Failure = false;

	private long interval_time_between_tasks = 5000;

	private int ssh_port_number = 22;

	private String error_msg_buffer_tmp_file_path = "output" + File.separator + "sshxcute_err.msg";
	
	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public boolean isHalt_on_Failure() {
		return halt_on_Failure;
	}

	public void setHalt_on_Failure(boolean halt_on_Failure) {
		this.halt_on_Failure = halt_on_Failure;
	}

	public long getInterval_time_between_tasks() {
		return interval_time_between_tasks;
	}

	public void setInterval_time_between_tasks(long interval_time_between_tasks) {
		this.interval_time_between_tasks = interval_time_between_tasks;
	}

	public int getSsh_port_number() {
		return ssh_port_number;
	}

	public void setSsh_port_number(int ssh_port_number) {
		this.ssh_port_number = ssh_port_number;
	}

	public String getError_msg_buffer_tmp_file_path() {
		return error_msg_buffer_tmp_file_path;
	}

	public void setError_msg_buffer_tmp_file_path(String error_msg_buffer_tmp_file_path) {
		this.error_msg_buffer_tmp_file_path = error_msg_buffer_tmp_file_path;
	}

	@Override
	public String toString() {
		return timeout + ", " + halt_on_Failure + ", " + interval_time_between_tasks + ", " + ssh_port_number + ", "
				+ error_msg_buffer_tmp_file_path;
	}
	
	
}
