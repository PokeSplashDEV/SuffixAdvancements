package org.pokesplash.suffixadvancements.config;

public class BalanceConfig {
	private String suffix;
	private double value;

	public BalanceConfig() {
		suffix = "[CountSuffix]";
		value = 10;
	}

	public String getSuffix() {
		return suffix;
	}

	public double getValue() {
		return value;
	}
}
