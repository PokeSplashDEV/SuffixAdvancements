package org.pokesplash.suffixadvancements.config;

public class BalanceConfig implements AdvancementConfig {
	private String suffix;
	private double value;
	private String displayItem;

	public BalanceConfig() {
		suffix = "[CountSuffix]";
		value = 10;
		displayItem = "minecraft:barrier";
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	public double getValue() {
		return value;
	}

	@Override
	public String getDisplayItem() {
		return displayItem;
	}
}
