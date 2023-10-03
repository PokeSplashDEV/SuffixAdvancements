package org.pokesplash.suffixadvancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.pokesplash.suffixadvancements.events.ImpactorEvent;
import org.pokesplash.suffixadvancements.events.LuckPermsEvent;

public class SuffixAdvancements implements ModInitializer {
	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		new ImpactorEvent().registerEvent();

		ServerLifecycleEvents.SERVER_STARTED.register(e -> {
			new LuckPermsEvent().registerEvent();
		});

	}
}
