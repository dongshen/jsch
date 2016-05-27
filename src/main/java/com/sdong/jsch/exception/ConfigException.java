package com.sdong.jsch.exception;

public class ConfigException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5685473191696610564L;
	private String reason;

	public ConfigException(String message) {
		super(message);
		this.reason = "Get server configuration error : " + message;
	}

	public String getMessage() {
		return reason;
	}

}
