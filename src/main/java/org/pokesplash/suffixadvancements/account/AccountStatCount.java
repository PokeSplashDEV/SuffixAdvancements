package org.pokesplash.suffixadvancements.account;

public class AccountStatCount {
	private boolean isComplete;
	private int count;

	public AccountStatCount() {
		isComplete = false;
		count = 0;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public int getCount() {
		return count;
	}

	public void setComplete(boolean complete) {
		isComplete = complete;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void addCount() {
		this.count += 1;
	}
}
