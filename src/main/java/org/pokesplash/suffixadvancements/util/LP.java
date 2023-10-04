package org.pokesplash.suffixadvancements.util;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.node.types.SuffixNode;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.config.AdvancementConfig;

import java.util.ArrayList;
import java.util.UUID;

public abstract class LP {
	public static void changeSuffix(SuffixNode suffix, UUID player) {
		LuckPermsProvider.get().getUserManager().modifyUser(player, e -> {

			ArrayList<Node> nodes = new ArrayList<>(e.data().toCollection());

			for (Node node : nodes) {
				if (node.getContexts().contains("mod", "suffixadvancements")) {
					e.data().remove(node);
				}
			}

//			// Remove all of our suffix nodes first.
//			for (SuffixNode node : SuffixAdvancements.nodes.getSuffixes()) {
//				e.data().remove(node);
//			}
//			e.data().remove(SuffixAdvancements.nodes.getPerfection());

			e.data().add(suffix);
		});
	}
}
