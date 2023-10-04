package org.pokesplash.suffixadvancements.config;

public class PermissionConfig implements AdvancementConfig {
	private String suffix;
	private String permission;

	public PermissionConfig() {
		suffix = "[PermissionSuffix]";
		permission = "advancements.defaultsuffix";
	}

	@Override
	public String getSuffix() {
		return suffix;
	}

	public String getPermission() {
		return permission;
	}
}
