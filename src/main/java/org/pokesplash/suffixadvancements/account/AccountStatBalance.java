package org.pokesplash.suffixadvancements.account;

public class AccountStatBalance implements AccountStatConfig {
	private boolean isComplete;
	private double count;

	public AccountStatBalance() {
		isComplete = false;
		count = 0;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public double getCount() {
		return count;
	}

	public void setComplete(boolean complete) {
		isComplete = complete;
	}

	public void setCount(short count) {
		this.count = count;
	}

	public void addCount(double count) {
		this.count += count;
	}
}
