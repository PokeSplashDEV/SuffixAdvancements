package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class BotanistEvent {
	public void registerEvent() {
		CobblemonEvents.BERRY_HARVEST.subscribe(Priority.NORMAL, el -> {
			Account acc = SuffixAdvancements.accounts.getAccount(el.getPlayer().getUuid());

			acc.getBotanist().addCount();

			if (acc.getBotanist().getCount() >= SuffixAdvancements.config.getBotanist().getValue()) {
				acc.getBotanist().setComplete(true);

				Perfectionist.updatePerfectionist(acc);
			}

			SuffixAdvancements.accounts.updateAccount(acc);
			return Unit.INSTANCE;
		});
	}
}
