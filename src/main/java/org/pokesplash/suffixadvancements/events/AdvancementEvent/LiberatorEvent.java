package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class LiberatorEvent {
	public void registerEvent() {
		// TODO pray mappings get fixed
//		CobblemonEvents.POKEMON_RELEASED_EVENT_POST.subscribe(Priority.NORMAL, el -> {
//			Account acc = SuffixAdvancements.accounts.getAccount(el.getPlayer().getUuid());
//			acc.getLiberator().addCount();
//
//			if (acc.getLiberator().getCount() >= SuffixAdvancements.config.getLiberator().getValue()) {
//				acc.getLiberator().setComplete(true);
//
//				if (Perfectionist.check(acc)) {
//					acc.setPerfectionist(true);
//				}
//			}
//
//			SuffixAdvancements.accounts.updateAccount(acc);
//			return Unit.INSTANCE;
//		});
	}
}