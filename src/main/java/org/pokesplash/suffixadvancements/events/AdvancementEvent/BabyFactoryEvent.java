package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import org.pokesplash.daycare.event.DayCareEvents;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class BabyFactoryEvent {
	public void registerEvent() {
		DayCareEvents.RETRIEVE_EGG.subscribe(e -> {
			Account acc = SuffixAdvancements.accounts.getAccount(e.getPlayer().getUuid());

			acc.getBabyFactory().addCount();

			if (acc.getBabyFactory().getCount() >= SuffixAdvancements.config.getBabyFactory().getValue()) {
				acc.getBabyFactory().setComplete(true);

				Perfectionist.updatePerfectionist(acc);
			}

			SuffixAdvancements.accounts.updateAccount(acc);
		});
	}


}
