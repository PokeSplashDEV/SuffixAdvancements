package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import org.pokesplash.gts.api.event.GtsEvents;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;

public class DealerEvent {
	public void registerEvent() {
		GtsEvents.PURCHASE.subscribe(el -> {
			Account account = SuffixAdvancements.accounts.getAccount(el.getProduct().getSellerUuid());
			account.getDealer().addCount();
			SuffixAdvancements.accounts.updateAccount(account);
		});
	}
}
