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

public class PrefixItem {
	private AdvancementConfig config;
	private AccountStatConfig account;
	private String message;

	public PrefixItem(AdvancementConfig config, AccountStatConfig account, String message) {
		this.config = config;
		this.account = account;
		this.message = message;
	}

	public GooeyButton getButton() {
		if (account.isComplete()) {
			Collection<String> lore = new ArrayList<>();
			lore.add("§aComplete");

			return GooeyButton.builder()
					.display(Utils.parseItemId(config.getDisplayItem()))
					.title(config.getSuffix())
					.onClick(e -> {
						LP.changeSuffix(SuffixAdvancements.nodes.getNode(config),
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
