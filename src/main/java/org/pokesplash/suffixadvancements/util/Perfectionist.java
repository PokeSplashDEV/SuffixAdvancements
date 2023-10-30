package org.pokesplash.suffixadvancements.util;

import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;

public abstract class Perfectionist {
	private static boolean check(Account account) {

		if (account.isPerfectionist()) {
			return false;
		}

		boolean dealer = account.getDealer().isComplete();
		boolean highroller = account.getHighroller().isComplete();
		boolean fortune = account.getFortune().isComplete();
		boolean babyFactory = account.getBabyFactory().isComplete();
		boolean writeoff = account.getWriteoff().isComplete();
		boolean camper = account.getCamper().isComplete();
		boolean bountyhunter = account.getBountyhunter().isComplete();
		boolean unrivaled = account.getUnrivaled().isComplete();
		boolean bot = account.getBot().isComplete();
		boolean botanist = account.getBotanist().isComplete();
		boolean ally = account.getAlly().isComplete();
		boolean toxic = account.getToxic().isComplete();
		boolean smurf = account.getSmurf().isComplete();
		boolean lucky = account.getLucky().isComplete();
		boolean quizmaster = account.getQuizmaster().isComplete();
		boolean onemore = account.getOnemore().isComplete();
		boolean liberator = account.getLiberator().isComplete();

		if (dealer && highroller && fortune && babyFactory && writeoff && camper && bountyhunter &&
		unrivaled && bot && botanist && ally && toxic && smurf && lucky &&
				quizmaster && onemore && liberator) {
			return true;
		}
		return false;
	}

	public static void updatePerfectionist(Account account) {
		if (check(account)) {
			account.setPerfectionist(true);
			SuffixAdvancements.accounts.updateAccount(account);
		}
	}
}
