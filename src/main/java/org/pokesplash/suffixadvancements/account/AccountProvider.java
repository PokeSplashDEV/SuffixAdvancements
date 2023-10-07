package org.pokesplash.suffixadvancements.account;

import com.google.gson.Gson;
import net.minecraft.block.PumpkinBlock;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.util.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AccountProvider {
	private HashMap<UUID, Account> accounts;

	public AccountProvider() {
		accounts = new HashMap<>();
	}


	public boolean hasAccount(UUID player) {
		return accounts.containsKey(player);
	}
	public Account getAccount(UUID playerUuid) {
		if (!accounts.containsKey(playerUuid)) {
			return createAccount(playerUuid);
		}
		return accounts.get(playerUuid);
	}

	public Account getAccount(String username) {
		ArrayList<Account> accountList = new ArrayList<>(accounts.values());

		for (Account ac : accountList) {
			if (ac.getUsername().equalsIgnoreCase(username)) {
				return ac;
			}
		}
		return null;
	}

	public Account createAccount(UUID playerUuid) {
		if (!accounts.containsKey(playerUuid)) {
			Account newAccount = new Account(playerUuid);
			accounts.put(playerUuid, newAccount);
			writeToFile(newAccount);
		}
		return accounts.get(playerUuid);
	}

	public void updateAccount(Account account) {
		accounts.put(account.getOwner(), account);
		writeToFile(account);
	}


	private void writeToFile(Account account) {
		Gson gson = Utils.newGson();
		String data = gson.toJson(account);

		CompletableFuture<Boolean> success = Utils.writeFileAsync("/config/suffixadvancements/accounts/",
				account.getOwner() + ".json", data);

		if (!success.join()) {
			SuffixAdvancements.LOGGER.fatal("Unable to write account to file for " + account.getOwner() +
					" for SuffixAdvancements.");
		}
	}

	public void init() {
		File dir = Utils.checkForDirectory("/config/suffixadvancements/accounts/");

		String[] list = dir.list();

		if (list.length == 0) {
			return;
		}

		for (String file : list) {
			Utils.readFileAsync("/config/suffixadvancements/accounts/", file, e -> {
				Gson gson = Utils.newGson();
				Account acc = gson.fromJson(e, Account.class);
				accounts.put(acc.getOwner(), acc);
			});
		}
	}
}
