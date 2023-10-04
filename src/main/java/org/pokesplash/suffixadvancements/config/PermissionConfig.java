package org.pokesplash.suffixadvancements.config;

public class PermissionConfig {
	private String suffix;
	private String permission;

	public PermissionConfig() {
		suffix = "[PermissionSuffix]";
		permission = "advancements.defaultsuffix";
	}

	public String getSuffix() {
		return suffix;
	}

	public String getPermission() {
		return permission;
	}
}
