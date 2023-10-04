package org.pokesplash.suffixadvancements.config;

public class CountConfig {
	private String suffix;
	private int value;

	public CountConfig() {
		suffix = "[CountSuffix]";
		value = 10;
	}

	public String getSuffix() {
		return suffix;
	}

	public int getValue() {
		return value;
	}
}
