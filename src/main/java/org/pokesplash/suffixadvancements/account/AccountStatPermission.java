package org.pokesplash.suffixadvancements.account;

public class AccountStatPermission implements AccountStatConfig {
	private boolean isComplete;

	public AccountStatPermission() {
		isComplete = false;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean complete) {
		isComplete = complete;
	}
}
