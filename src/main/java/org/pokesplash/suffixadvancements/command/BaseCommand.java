package org.pokesplash.suffixadvancements.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class BaseCommand {
	public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> root = CommandManager
				.literal("suffixadvancements")
				.requires(ctx -> {
					if (ctx.isExecutedByPlayer()) {
						// TODO check permissions
						return true;
					} else {
						return true;
					}
				})
				.executes(this::run);

		LiteralCommandNode<ServerCommandSource> registeredCommand = dispatcher.register(root);

		dispatcher.register(CommandManager.literal("adv").redirect(registeredCommand).executes(this::run));
		dispatcher.register(CommandManager.literal("suffixadv").redirect(registeredCommand).executes(this::run));

		registeredCommand.addChild(new ReloadCommand().build());

	}

	public int run(CommandContext<ServerCommandSource> context) {
		// TODO do something
		System.out.println("Base command run");
		return 1;
	}
}
