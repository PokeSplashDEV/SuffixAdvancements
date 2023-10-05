package org.pokesplash.suffixadvancements.util;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.SuffixNode;

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

			e.data().add(suffix);
		});
	}
}
