package org.pokesplash.suffixadvancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pokesplash.suffixadvancements.account.AccountProvider;
import org.pokesplash.suffixadvancements.config.Config;
import org.pokesplash.suffixadvancements.events.AdvancementEvent.ImpactorEvent;
import org.pokesplash.suffixadvancements.events.JoinEvent;

public class SuffixAdvancements implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final Config config = new Config();
	public static final AccountProvider accounts = new AccountProvider();

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		new ImpactorEvent().registerEvent();

		ServerLifecycleEvents.SERVER_STARTED.register(e -> {
			config.init();
			accounts.init();
			ServerPlayConnectionEvents.JOIN.register(new JoinEvent());
		});

	}
}
