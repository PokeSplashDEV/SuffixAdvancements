package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class LuckyEvent {
	public void registerEvent() {
		CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.NORMAL, el -> {
			if (el.getPokemon().getShiny()) {
				Account acc = SuffixAdvancements.accounts.getAccount(el.getPlayer().getUuid());
				acc.getLucky().addCount();

				if (acc.getLucky().getCount() >= SuffixAdvancements.config.getLucky().getValue()) {
					acc.getLucky().setComplete(true);

					if (Perfectionist.check(acc)) {
						acc.setPerfectionist(true);
					}
				}

				SuffixAdvancements.accounts.updateAccount(acc);
			}
			return Unit.INSTANCE;
		});
	}
}
