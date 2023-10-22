package org.pokesplash.suffixadvancements.ui;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.button.PlaceholderButton;
import ca.landonjw.gooeylibs2.api.helpers.PaginationHelper;
import ca.landonjw.gooeylibs2.api.page.LinkedPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.data.JsonDataRegistry;
import com.cobblemon.mod.common.api.pokeball.catching.calculators.PokedexProgressCaptureMultiplierProvider;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.Species;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.pokesplash.hunt.Hunt;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.config.Config;
import org.pokesplash.suffixadvancements.util.LP;
import org.pokesplash.suffixadvancements.util.Utils;

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
		page.setTitle("§9Choose a Prefix");

		return page;
	}

	private ArrayList<Button> getSuffixButtons(ServerPlayerEntity player) {
		Config cfg = SuffixAdvancements.config;
		Account account = SuffixAdvancements.accounts.getAccount(player.getUuid());

		ArrayList<Button> buttons = new ArrayList<>();

		Collection<String> lore = new ArrayList<>();
		lore.add("§aRemove your active Prefix.");
		buttons.add(GooeyButton.builder()
				.display(new ItemStack(Items.END_CRYSTAL))
				.title("§2Remove Prefix")
				.onClick(e -> {
					LP.removeSuffix(e.getPlayer().getUuid());

					UIManager.closeUI(e.getPlayer());
				})
				.lore(lore)
				.build());

		buttons.add(new PrefixItem(cfg.getDealer(), account.getDealer(),
				"Sell " + cfg.getDealer().getValue() + " Pokemon on GTS.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getHighroller(), account.getHighroller(),
				"Spend " + cfg.getHighroller().getValue() + " dollars.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getFortune(), account.getFortune(),
				"Spend " + cfg.getFortune().getValue() + " dollars.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getWriteoff(), account.getWriteoff(),
				"Purchase the {item} from the Shop.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getCamper(), account.getCamper(),
				"Complete the Pokedex.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getBountyhunter(), account.getBountyhunter(),
				"Hunt " + cfg.getBountyhunter().getValue() + " Pokemon in Hunt.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getUnrivaled(), account.getUnrivaled(),
				"Win " + cfg.getUnrivaled().getValue() + " battles.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getBot(), account.getBot(),
				"Lose " + cfg.getBot().getValue() + " battles.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getBotanist(), account.getBotanist(),
				"Harvest " + cfg.getBotanist().getValue() + " berries.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getAlly(), account.getAlly(),
				"Vote " + cfg.getAlly().getValue() + " times.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getToxic(), account.getToxic(),
				"Sell " + cfg.getToxic().getValue() + " Legendary Pokemon on STS.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getSmurf(), account.getSmurf(),
				"Prestige after the first 8 Gyms.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getLucky(), account.getLucky(),
				"Catch " + cfg.getLucky().getValue() + " shiny Pokemon.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getQuizmaster(), account.getQuizmaster(),
				"Answer " + cfg.getQuizmaster().getValue() + " Trivia questions correctly.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getOnemore(), account.getOnemore(),
				"Purchase " + cfg.getOnemore().getValue() + " Shiny Boosts from the shop.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getLiberator(), account.getLiberator(),
				"Release " + cfg.getLiberator().getValue() + " Pokemon.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getChampion(), account.getChampion(),
				"Become Champion of the gyms.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getStakeholder(), account.getStakeholder(),
				"Donate to us.", player.getUuid()).getButton());
		buttons.add(new PrefixItem(cfg.getPrimordial(), account.getPrimordial(),
				"Play within the first two weeks of the server opening.", player.getUuid()).getButton());
		buttons.add(getPerfectionist(account.isPerfectionist(), player));

		return buttons;
	}

	private GooeyButton getPerfectionist(boolean isPerfection, ServerPlayerEntity player) {
		GooeyButton.Builder button = GooeyButton.builder()
				.title(SuffixAdvancements.config.getPerfectionist());

		if (LP.hasNode(SuffixAdvancements.nodes.getPerfection(), player.getUuid())) {
			Collection<String> lore = new ArrayList<>();
			lore.add("§6You currently have this Prefix enabled.");
			return GooeyButton.builder()
					.title(SuffixAdvancements.config.getPerfectionist())
					.display(new ItemStack(Items.NETHER_STAR))
					.lore(lore)
					.build();
		}

		if (isPerfection) {
			button.onClick(e -> {
				LP.changeSuffix(SuffixAdvancements.nodes.getPerfection(),
						e.getPlayer().getUuid());

				e.getPlayer().sendMessage(Text.literal("§2Changed Prefix to " +
						SuffixAdvancements.config.getPerfectionist()));

				UIManager.closeUI(e.getPlayer());
			});
			button.display(new ItemStack(Items.NETHER_STAR));
		} else {
			Collection<String> lore = new ArrayList<>();
			lore.add("§bComplete all other Advancements.");
			lore.add("§3Excluding:");
			lore.add("§3- Become Champion of the gyms.");
			lore.add("§3- Donate to us.");
			lore.add("§3- Play within the first two weeks of the server opening.");

			button.display(new ItemStack(Items.BARRIER));
			button.lore(lore);
		}
		return button.build();
	}
}
