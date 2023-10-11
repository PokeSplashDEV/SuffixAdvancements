package org.pokesplash.suffixadvancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pokesplash.suffixadvancements.account.AccountProvider;
import org.pokesplash.suffixadvancements.command.CommandHandler;
import org.pokesplash.suffixadvancements.config.Config;
import org.pokesplash.suffixadvancements.config.NodeProvider;
import org.pokesplash.suffixadvancements.events.AdvancementEvent.*;
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
		CommandRegistrationCallback.EVENT.register(CommandHandler::registerCommands);
		ServerPlayConnectionEvents.JOIN.register(new JoinEvent()); // Creates an account.

		ServerLifecycleEvents.SERVER_STARTED.register(e -> {
			load();

			/*
			 * Register the events.
			 */

			new HighRollerAndFortuneEvent().registerEvent();
			new DealerEvent().registerEvent();
			new LuckPermsEvent().registerEvent();
			new UnrivaledAndBotEvent().registerEvent();
			new BotanistEvent().registerEvent();
			new LuckyEvent().registerEvent();
			new LiberatorEvent().registerEvent();
			new BountyHunterEvent().registerEvent();
			new QuizMasterEvent().registerEvent();
			new ToxicEvent().registerEvent();
		});
	}

	public static void load() {
		config.init();
		accounts.init();
		nodes.init();
	}
}
