package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class BotanistEvent {
	public void registerEvent() {
		// TODO pray mappings get fixed
//		CobblemonEvents.BERRY_HARVESTED.subscribe(Priority.NORMAL, el -> {
//			Account acc = SuffixAdvancements.accounts.getAccount(el.getPlayer().getUuid());
//			acc.getBotanist().addCount();
//
//			if (acc.getBotanist().getCount() >= SuffixAdvancements.config.getBotanist().getValue()) {
//				acc.getBotanist().setComplete(true);
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
