package org.pokesplash.suffixadvancements.config;

public class StandardConfig implements AdvancementConfig {

	private String suffix;
	private String displayItem;

	public StandardConfig() {
		suffix = "[PermissionSuffix]";
		displayItem = "minecraft:barrier";
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	@Override
	public String getDisplayItem() {
		return displayItem;
	}
}
