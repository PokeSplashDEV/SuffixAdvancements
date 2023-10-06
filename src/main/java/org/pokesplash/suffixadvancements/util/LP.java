package org.pokesplash.suffixadvancements.util;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.StaticContextCalculator;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.metadata.NodeMetadataKey;
import net.luckperms.api.node.types.SuffixNode;

import java.util.ArrayList;
import java.util.UUID;

public abstract class LP {
	public static void changeSuffix(SuffixNode suffix, UUID player) {
		LuckPermsProvider.get().getUserManager().modifyUser(player, e -> {

			ArrayList<Node> nodes = new ArrayList<>(e.data().toCollection());

			for (Node node : nodes) {

				if (node instanceof SuffixNode) {
					if (((SuffixNode) node).getPriority() == 41) {
						e.data().remove(node);
					}
				}
			}

			e.data().add(suffix);
		});
	}
}
