package org.pokesplash.suffixadvancements;

import ca.landonjw.gooeylibs2.api.button.linked.LinkedPageButton;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pokesplash.suffixadvancements.account.AccountProvider;
import org.pokesplash.suffixadvancements.config.Config;
import org.pokesplash.suffixadvancements.config.NodeProvider;
import org.pokesplash.suffixadvancements.events.AdvancementEvent.HighRollerAndFortuneEvent;
import org.pokesplash.suffixadvancements.events.JoinEvent;
import org.pokesplash.suffixadvancements.util.LP;

import java.util.UUID;

public class SuffixAdvancements implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final Config config = new Config();
	public static final AccountProvider accounts = new AccountProvider();
	public static final NodeProvider nodes = new NodeProvider();

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(e -> {
			config.init();
			accounts.init();
			nodes.init();

			/*
			 * Register the events.
			 */

			ServerPlayConnectionEvents.JOIN.register(new JoinEvent()); // Creates an account.
			new HighRollerAndFortuneEvent().registerEvent();

			LP.changeSuffix(nodes.getNode(config.getHighroller()),
					UUID.fromString("b5c833a0-c6f7-4e89-9ad5-d36faef37ab2")); // TODO remove
		});

	}
}
