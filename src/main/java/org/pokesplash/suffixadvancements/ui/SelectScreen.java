package org.pokesplash.suffixadvancements.ui;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.pokesplash.hunt.Hunt;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.config.Config;
import org.pokesplash.suffixadvancements.util.LP;

import java.util.ArrayList;
import java.util.Collection;

public class SelectScreen {
	public Page open(ServerPlayerEntity player) {
		ArrayList<Button> buttons = getSuffixButtons(player);

		PlaceholderButton placeholder = new PlaceholderButton();

		ChestTemplate template = ChestTemplate.builder(3)
				.rectangle(0, 0, 3, 9, placeholder)
				.build();

		LinkedPage page = PaginationHelper.createPagesFromPlaceholders(template, buttons, null);
		page.setTitle(Hunt.language.getTitle());

		return page;
	}

	private ArrayList<Button> getSuffixButtons(ServerPlayerEntity player) {
		Config cfg = SuffixAdvancements.config;
		Account account = SuffixAdvancements.accounts.getAccount(player.getUuid());

		ArrayList<Button> buttons = new ArrayList<>();

		buttons.add(new PrefixItem(cfg.getDealer(), account.getDealer(),
				"Sell " + cfg.getDealer().getValue() + " Pokemon on GTS.").getButton());
		buttons.add(new PrefixItem(cfg.getHighroller(), account.getHighroller(),
				"Spend " + cfg.getHighroller().getValue() + " dollars.").getButton());
		buttons.add(new PrefixItem(cfg.getFortune(), account.getFortune(),
				"Spend " + cfg.getFortune().getValue() + " dollars.").getButton());
		buttons.add(new PrefixItem(cfg.getWriteoff(), account.getWriteoff(),
				"Purchase the {item} from the shop.").getButton());
		buttons.add(new PrefixItem(cfg.getCamper(), account.getCamper(),
				"Complete the Pokedex.").getButton());
		buttons.add(new PrefixItem(cfg.getBountyhunter(), account.getBountyhunter(),
				"Hunt " + cfg.getBountyhunter().getValue() + " Pokemon in Hunt.").getButton());
		buttons.add(new PrefixItem(cfg.getUnrivaled(), account.getUnrivaled(),
				"Win " + cfg.getUnrivaled().getValue() + " battles.").getButton());
		buttons.add(new PrefixItem(cfg.getBot(), account.getBot(),
				"Lose " + cfg.getBot().getValue() + " battles.").getButton());
		buttons.add(new PrefixItem(cfg.getBotanist(), account.getBotanist(),
				"Harvest " + cfg.getBotanist().getValue() + " berries.").getButton());
		buttons.add(new PrefixItem(cfg.getAlly(), account.getAlly(),
				"Vote " + cfg.getAlly().getValue() + " times.").getButton());
		buttons.add(new PrefixItem(cfg.getToxic(), account.getToxic(),
				"Sell " + cfg.getToxic().getValue() + " Legendary Pokemon on GTS.").getButton());
		buttons.add(new PrefixItem(cfg.getSmurf(), account.getSmurf(),
				"Prestige after the first 8 Gyms.").getButton());
		buttons.add(new PrefixItem(cfg.getLucky(), account.getLucky(),
				"Catch " + cfg.getLucky().getValue() + " shiny Pokemon.").getButton());
		buttons.add(new PrefixItem(cfg.getQuizmaster(), account.getQuizmaster(),
				"Answer " + cfg.getQuizmaster().getValue() + " Trivia questions successfully.").getButton());
		buttons.add(new PrefixItem(cfg.getOnemore(), account.getOnemore(),
				"Purchase " + cfg.getOnemore().getValue() + " Shiny Boosts from the shop.").getButton());
		buttons.add(new PrefixItem(cfg.getLiberator(), account.getLiberator(),
				"Release " + cfg.getLiberator().getValue() + " Pokemon.").getButton());
		buttons.add(new PrefixItem(cfg.getChampion(), account.getChampion(),
				"Become Champion of the gyms.").getButton());
		buttons.add(new PrefixItem(cfg.getStakeholder(), account.getStakeholder(),
				"Donate to us.").getButton());
		buttons.add(new PrefixItem(cfg.getPrimordial(), account.getPrimordial(),
				"Play within the first two weeks of the server opening.").getButton());
		buttons.add(getPerfectionist(account.isPerfectionist()));

		return buttons;
	}

	private GooeyButton getPerfectionist(boolean isPerfection) {
		GooeyButton.Builder button = GooeyButton.builder()
				.display(new ItemStack(Items.NETHER_STAR))
				.title(SuffixAdvancements.config.getPerfectionist());
		if (isPerfection) {
			button.onClick(e -> {
				LP.changeSuffix(SuffixAdvancements.nodes.getPerfection(),
						e.getPlayer().getUuid());

				e.getPlayer().sendMessage(Text.literal("§2Changed Prefix to " +
						SuffixAdvancements.config.getPerfectionist()));

				UIManager.closeUI(e.getPlayer());
			});
		} else {
			Collection<String> lore = new ArrayList<>();
			lore.add("§bComplete all other Advancements.");
			lore.add("§3Excluding:");
			lore.add("§3- Become Champion of the gyms.");
			lore.add("§3- Donate to us.");
			lore.add("§3- Play within the first two weeks of the server opening.");

			button.lore(lore);
		}
		return button.build();
	}
}
