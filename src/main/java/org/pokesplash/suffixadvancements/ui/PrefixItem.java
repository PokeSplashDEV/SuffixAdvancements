package org.pokesplash.suffixadvancements.ui;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.AccountStatBalance;
import org.pokesplash.suffixadvancements.account.AccountStatConfig;
import org.pokesplash.suffixadvancements.account.AccountStatCount;
import org.pokesplash.suffixadvancements.config.AdvancementConfig;
import org.pokesplash.suffixadvancements.config.BalanceConfig;
import org.pokesplash.suffixadvancements.config.CountConfig;
import org.pokesplash.suffixadvancements.util.LP;
import org.pokesplash.suffixadvancements.util.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class PrefixItem {
	private AdvancementConfig config;
	private AccountStatConfig account;
	private String message;
	private UUID player;

	public PrefixItem(AdvancementConfig config, AccountStatConfig account, String message, UUID player) {
		this.config = config;
		this.account = account;
		this.message = message;
		this.player = player;
	}

	public GooeyButton getButton() {

		if (LP.hasNode(SuffixAdvancements.nodes.getNode(config), player)) {
			Collection<String> lore = new ArrayList<>();
			lore.add("§6You currently have this Prefix enabled.");
			return GooeyButton.builder()
					.display(Utils.parseItemId(config.getDisplayItem()))
					.title(config.getSuffix())
					.lore(lore)
					.build();
		}

		if (account.isComplete()) {
			Collection<String> lore = new ArrayList<>();
			lore.add("§aComplete");

			return GooeyButton.builder()
					.display(Utils.parseItemId(config.getDisplayItem()))
					.title(config.getSuffix())
					.onClick(e -> {
						LP.changePrefix(SuffixAdvancements.nodes.getNode(config),
								e.getPlayer().getUuid());

						e.getPlayer().sendMessage(Text.literal("§2Changed Prefix to " + config.getSuffix()));

						UIManager.closeUI(e.getPlayer());
					})
					.lore(lore)
					.build();
		} else {
			Collection<String> lore = new ArrayList<>();
			lore.add("§b" + message);
			lore.addAll(generateLore());
			return GooeyButton.builder()
					.display(new ItemStack(Items.BARRIER))
					.title(config.getSuffix())
					.lore(lore)
					.build();
		}
	}

	private Collection<String> generateLore() {

		Collection<String> lore = new ArrayList<>();

		if (config instanceof BalanceConfig) {
			AccountStatBalance acc = (AccountStatBalance) account ;
			BalanceConfig cfg = (BalanceConfig) config;
			lore.add("§cProgress: " + acc.getCount() + "/" + cfg.getValue());
		} else if (config instanceof CountConfig) {
			AccountStatCount acc = (AccountStatCount) account ;
			CountConfig cfg = (CountConfig) config;
			lore.add("§cProgress: " + acc.getCount() + "/" + cfg.getValue());
		} else {
			lore.add("§cIncomplete");
		}

		return lore;
	}
}
