package org.pokesplash.suffixadvancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pokesplash.suffixadvancements.config.Config;
import org.pokesplash.suffixadvancements.events.ImpactorEvent;
import org.pokesplash.suffixadvancements.events.LuckPermsEvent;

public class SuffixAdvancements implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger();
	public static final Config config = new Config();

	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		new ImpactorEvent().registerEvent();

		ServerLifecycleEvents.SERVER_STARTED.register(e -> {
			config.init();
			new LuckPermsEvent().registerEvent();
		});

	}
}
