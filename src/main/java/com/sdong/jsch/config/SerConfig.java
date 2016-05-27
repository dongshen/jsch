package com.sdong.jsch.config;

public class SerConfig {
	String serverIP = "";
	String user = "";
	String password = "";
	String command = "";

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	@Override
	public String toString() {
		return serverIP + ", " + user + ", " + password + ", " + command;
	}

}
