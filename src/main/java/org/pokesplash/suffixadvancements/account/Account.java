package org.pokesplash.suffixadvancements.account;

import java.util.UUID;

public class Account {
	private UUID owner;
	private String username;
	private AccountStatCount dealer;
	private AccountStatBalance highroller;
	private AccountStatBalance fortune;
	private AccountStatPermission writeoff;
	private AccountStatPermission camper;
	private AccountStatCount bountyhunter;
	private AccountStatCount unrivaled;
	private AccountStatCount bot;
	private AccountStatCount botanist;
	private AccountStatCount babyfactory;
	private AccountStatCount ally;
	private AccountStatCount toxic;
	private AccountStatPermission smurf;
	private AccountStatCount lucky;
	private AccountStatCount habitue;
	private AccountStatCount onemore;
	private AccountStatCount liberator;
	private boolean perfectionist;
	private AccountStatPermission champion;
	private AccountStatPermission stakeholder;
	private AccountStatPermission primordial;

	public Account(UUID id) {
		owner = id;
		dealer = new AccountStatCount();
		highroller = new AccountStatBalance();
		fortune = new AccountStatBalance();
		writeoff = new AccountStatPermission();
		camper = new AccountStatPermission();
		bountyhunter = new AccountStatCount();
		unrivaled = new AccountStatCount();
		bot = new AccountStatCount();
		botanist = new AccountStatCount();
		babyfactory = new AccountStatCount();
		ally = new AccountStatCount();
		toxic = new AccountStatCount();
		smurf = new AccountStatPermission();
		lucky = new AccountStatCount();
		habitue = new AccountStatCount();
		onemore = new AccountStatCount();
		liberator = new AccountStatCount();
		perfectionist = false;
		champion = new AccountStatPermission();
		stakeholder = new AccountStatPermission();
		primordial = new AccountStatPermission();
	}

	public UUID getOwner() {
		return owner;
	}

	public AccountStatCount getDealer() {
		return dealer;
	}

	public AccountStatBalance getHighroller() {
		return highroller;
	}

	public AccountStatBalance getFortune() {
		return fortune;
	}

	public AccountStatPermission getWriteoff() {
		return writeoff;
	}

	public AccountStatPermission getCamper() {
		return camper;
	}

	public AccountStatCount getBountyhunter() {
		return bountyhunter;
	}

	public AccountStatCount getUnrivaled() {
		return unrivaled;
	}

	public AccountStatCount getBot() {
		return bot;
	}

	public AccountStatCount getBotanist() {
		return botanist;
	}

	public AccountStatCount getBabyfactory() {
		return babyfactory;
	}

	public AccountStatCount getAlly() {
		return ally;
	}

	public AccountStatCount getToxic() {
		return toxic;
	}

	public AccountStatPermission getSmurf() {
		return smurf;
	}

	public AccountStatCount getLucky() {
		return lucky;
	}

	public AccountStatCount getHabitue() {
		return habitue;
	}

	public AccountStatCount getOnemore() {
		return onemore;
	}

	public AccountStatCount getLiberator() {
		return liberator;
	}

	public boolean isPerfectionist() {
		return perfectionist;
	}

	public AccountStatPermission getChampion() {
		return champion;
	}

	public AccountStatPermission getStakeholder() {
		return stakeholder;
	}

	public AccountStatPermission getPrimordial() {
		return primordial;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
