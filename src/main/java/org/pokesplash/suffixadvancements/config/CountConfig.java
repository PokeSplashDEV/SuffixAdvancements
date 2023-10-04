package org.pokesplash.suffixadvancements.config;

public class CountConfig implements AdvancementConfig {
	private String suffix;
	private int value;

	public CountConfig() {
		suffix = "[CountSuffix]";
		value = 10;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	public int getValue() {
		return value;
	}
}
