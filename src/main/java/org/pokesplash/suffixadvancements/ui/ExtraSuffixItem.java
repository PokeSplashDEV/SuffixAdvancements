package org.pokesplash.suffixadvancements.ui;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.util.LP;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class ExtraSuffixItem {
	private String permission;
	private String prefix;
	private UUID player;

	public ExtraSuffixItem(String permission, String prefix, UUID player) {
		this.permission = permission;
		this.prefix = prefix;
		this.player = player;
	}

	public GooeyButton getButton() {

		if (LP.hasNode(SuffixAdvancements.nodes.getExtraSuffix(permission), player)) {
			Collection<String> lore = new ArrayList<>();
			lore.add("§6You currently have this Suffix enabled.");
			return GooeyButton.builder()
					.display(new ItemStack(Items.NETHER_STAR))
					.title(prefix)
					.lore(lore)
					.build();
		}

		return GooeyButton.builder()
				.display(new ItemStack(Items.NETHER_STAR))
				.title(prefix)
				.onClick(e -> {
					LP.changeSuffix(SuffixAdvancements.nodes.getExtraSuffix(permission),
							e.getPlayer().getUuid());

					e.getPlayer().sendMessage(Text.literal("§2Changed Suffix to " + prefix));

					UIManager.closeUI(e.getPlayer());
				})
				.build();
	}
}
