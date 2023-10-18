package org.pokesplash.suffixadvancements.config;

public class PermissionConfig implements AdvancementConfig {
	private String suffix;
	private String permission;
	private String displayItem;

	public PermissionConfig() {
		suffix = "[PermissionSuffix]";
		permission = "advancements.defaultsuffix";
		displayItem = "minecraft:barrier";
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	public String getPermission() {
		return permission;
	}

	@Override
	public String getDisplayItem() {
		return displayItem;
	}
}
