package org.pokesplash.suffixadvancements.util;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeEqualityPredicate;
import net.luckperms.api.node.types.PrefixNode;
import net.minecraft.server.network.ServerPlayerEntity;
import org.pokesplash.suffixadvancements.SuffixAdvancements;

import java.util.ArrayList;
import java.util.UUID;

public abstract class LP {
	public static void changeSuffix(PrefixNode suffix, UUID player) {
		LuckPermsProvider.get().getUserManager().modifyUser(player, e -> {

			ArrayList<Node> nodes = new ArrayList<>(e.data().toCollection());

			for (Node node : nodes) {

				if (node instanceof PrefixNode) {
					if (((PrefixNode) node).getPriority() == 351) {
						e.data().remove(node);
					}
				}
			}
			e.data().add(suffix);
		});
	}

	public static void removeSuffix(UUID player) {
		LuckPermsProvider.get().getUserManager().modifyUser(player, e -> {

			ArrayList<Node> nodes = new ArrayList<>(e.data().toCollection());

			for (Node node : nodes) {

				if (node instanceof PrefixNode) {
					if (((PrefixNode) node).getPriority() == 351) {
						e.data().remove(node);
					}
				}
			}
		});
	}

	/**
	 * Checks a user has a given permission.
	 * @param user The user to check the permission on.
	 * @param permission The permission to check the user has.
	 * @return true if the user has the permission.
	 */
	public static boolean hasPermission(ServerPlayerEntity user, String permission) {
		User playerLP = LuckPermsProvider.get().getUserManager().getUser(user.getUuid());

		if (playerLP == null) {
			SuffixAdvancements.LOGGER.error("Could not find player " + user + " in LuckPerms.");
			return false;
		}

		return playerLP.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
	}
	public static boolean hasPermission(UUID user, String permission) {
		User playerLP = LuckPermsProvider.get().getUserManager().getUser(user);

		if (playerLP == null) {
			SuffixAdvancements.LOGGER.error("Could not find player " + user + " in LuckPerms.");
			return false;
		}

		return playerLP.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
	}

	public static boolean hasNode(Node suffix, UUID player) {
		ArrayList<Node> nodes =
				new ArrayList<>(LuckPermsProvider.get().getUserManager().getUser(player).data().toCollection());

		for (Node node : nodes) {

			if (node.equals(suffix, NodeEqualityPredicate.ONLY_KEY)) {
				return true;
			}
		}
		return false;
	}
}
