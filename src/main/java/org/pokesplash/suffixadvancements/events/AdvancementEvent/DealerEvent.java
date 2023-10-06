package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import org.pokesplash.gts.api.event.GtsEvents;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class DealerEvent {
	public void registerEvent() {
		GtsEvents.PURCHASE.subscribe(el -> {
			Account account = SuffixAdvancements.accounts.getAccount(el.getProduct().getSellerUuid());
			account.getDealer().addCount();

			if (account.getDealer().getCount() >= SuffixAdvancements.config.getDealer().getValue()) {
				account.getDealer().setComplete(true);

				if (Perfectionist.check(account)) {
					account.setPerfectionist(true);
				}
			}

			SuffixAdvancements.accounts.updateAccount(account);
		});
	}
}
