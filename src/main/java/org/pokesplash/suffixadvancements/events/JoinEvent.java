package org.pokesplash.suffixadvancements.events;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;

public class JoinEvent implements ServerPlayConnectionEvents.Join {
	@Override
	public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
		Account account = SuffixAdvancements.accounts.createAccount(handler.getPlayer().getUuid());

		handler.getPlayer().getStatHandler();

		// If usernames don't match, update it.
		if (!handler.getPlayer().getName().getString().equalsIgnoreCase(account.getUsername())) {
			account.setUsername(handler.getPlayer().getName().getString());
			SuffixAdvancements.accounts.updateAccount(account);
		}

		// Checks primordial is active, then sets it for anyone who joins.
		if (SuffixAdvancements.config.isPrimordialActive()) {
			if (!account.getPrimordial().isComplete()) {
				account.getPrimordial().setComplete(true);
			}
		}
	}
}
