package org.pokesplash.suffixadvancements.config;

public class BalanceConfig implements AdvancementConfig {
	private String suffix;
	private double value;

	public BalanceConfig() {
		suffix = "[CountSuffix]";
		value = 10;
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	public double getValue() {
		return value;
	}
}
