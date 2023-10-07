package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import network.roanoke.trivia.Events.TriviaEvents;
import network.roanoke.trivia.Trivia;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.Perfectionist;

import java.util.UUID;

public class QuizMasterEvent {
	public void registerEvent() {
		TriviaEvents.CORRECT_ANSWER.subscribe(el -> {
			Account account = SuffixAdvancements.accounts.getAccount(el.getPlayer());
			account.getQuizmaster().addCount();

			if (account.getQuizmaster().getCount() >= SuffixAdvancements.config.getQuizmaster().getValue()) {
				account.getQuizmaster().setComplete(true);
				Perfectionist.updatePerfectionist(account);
			}
			SuffixAdvancements.accounts.updateAccount(account);
		});
	}
}
