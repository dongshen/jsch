package com.sdong.jsch.config;

import java.util.ArrayList;

public class ServerSetting {

	private DefaultSetting defaultSetting = new DefaultSetting();

	private ArrayList<SerConfig> serConfig = new ArrayList<SerConfig>();

	public ArrayList<SerConfig> getSerConfig() {
		return serConfig;
	}

	public void setSerConfig(ArrayList<SerConfig> serConfig) {
		this.serConfig = serConfig;
	}

	public DefaultSetting getDefaultSetting() {
		return defaultSetting;
	}

	public void setDefaultSetting(DefaultSetting defaultSetting) {
		this.defaultSetting = defaultSetting;
	}

	@Override
	public String toString() {
		return defaultSetting + ", " + serConfig;
	}

}
