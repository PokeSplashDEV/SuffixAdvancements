package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import net.impactdev.impactor.api.economy.events.EconomyTransactionEvent;
import net.impactdev.impactor.api.economy.transactions.details.EconomyTransactionType;
import net.impactdev.impactor.api.events.ImpactorEventBus;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class HighRollerAndFortuneEvent {
	public void registerEvent() {
		ImpactorEventBus.bus().subscribe(EconomyTransactionEvent.Post.class, (e) -> {

			if (e.transaction().type().equals(EconomyTransactionType.WITHDRAW)) {
				Account account = SuffixAdvancements.accounts.getAccount(e.account().owner());

				double amount = e.transaction().amount().doubleValue();

				account.getHighroller().addCount(amount);
				account.getFortune().addCount(amount);

				if (account.getHighroller().getCount() >= SuffixAdvancements.config.getHighroller().getValue()) {
					account.getHighroller().setComplete(true);

					if (Perfectionist.check(account)) {
						account.setPerfectionist(true);
					}
				}

				if (account.getFortune().getCount() >= SuffixAdvancements.config.getFortune().getValue()) {
					account.getFortune().setComplete(true);

					if (Perfectionist.check(account)) {
						account.setPerfectionist(true);
					}
				}

				SuffixAdvancements.accounts.updateAccount(account);
			}


		});
	}
}
