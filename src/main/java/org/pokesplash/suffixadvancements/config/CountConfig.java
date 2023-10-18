package org.pokesplash.suffixadvancements.config;

public class CountConfig implements AdvancementConfig {
	private String suffix;
	private int value;

	private String displayItem;

	public CountConfig() {
		suffix = "[CountSuffix]";
		value = 10;
		displayItem = "minecraft:barrier";
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String getDisplayItem() {
		return displayItem;
	}
}
