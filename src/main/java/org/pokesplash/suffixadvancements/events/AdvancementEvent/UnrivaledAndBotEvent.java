package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import kotlin.Unit;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class UnrivaledAndBotEvent {
	public void registerEvent() {
		CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, (el) -> {
			// Unrivaled
			for (BattleActor player : el.getWinners()) {
				Account acc = SuffixAdvancements.accounts.getAccount(player.getUuid());
				acc.getUnrivaled().addCount();

				if (acc.getUnrivaled().getCount() >= SuffixAdvancements.config.getUnrivaled().getValue()) {
					acc.getUnrivaled().setComplete(true);

					Perfectionist.updatePerfectionist(acc);
				}

				SuffixAdvancements.accounts.updateAccount(acc);
			}

			// Bot
			for (BattleActor player : el.getLosers()) {
				Account acc = SuffixAdvancements.accounts.getAccount(player.getUuid());
				acc.getBot().addCount();

				if (acc.getBot().getCount() >= SuffixAdvancements.config.getBot().getValue()) {
					acc.getBot().setComplete(true);

					Perfectionist.updatePerfectionist(acc);
				}

				SuffixAdvancements.accounts.updateAccount(acc);
			}
			return Unit.INSTANCE;
		});
	}
}
