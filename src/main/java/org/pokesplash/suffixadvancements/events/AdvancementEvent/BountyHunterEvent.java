package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import org.pokesplash.hunt.api.event.HuntEvents;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class BountyHunterEvent {
	public void registerEvent() {
		HuntEvents.COMPLETED.subscribe(el -> {
			Account account = SuffixAdvancements.accounts.getAccount(el.getPlayer());
			account.getBountyhunter().addCount();

			if (account.getBountyhunter().getCount() >= SuffixAdvancements.config.getBountyhunter().getValue()) {
				account.getBountyhunter().setComplete(true);

				if (Perfectionist.check(account)) {
					account.setPerfectionist(true);
				}
			}
			SuffixAdvancements.accounts.updateAccount(account);
		});
	}
}
