package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import org.pokesplash.sts.api.event.StsEvents;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class ToxicEvent {
	public void registerEvent() {
		StsEvents.SELL.subscribe(el -> {
			if (el.getPokemon().isLegendary() || el.getPokemon().isUltraBeast()) {
				Account account = SuffixAdvancements.accounts.getAccount(el.getSeller());
				account.getToxic().addCount();

				if (account.getToxic().getCount() >= SuffixAdvancements.config.getToxic().getValue()) {
					account.getToxic().setComplete(true);

					Perfectionist.updatePerfectionist(account);
				}

				SuffixAdvancements.accounts.updateAccount(account);
			}
		});
	}
}
