package org.pokesplash.suffixadvancements.command;

import ca.landonjw.gooeylibs2.api.UIManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.ui.SelectScreen;
import org.pokesplash.suffixadvancements.util.LP;

public class BaseCommand {
	public void register(CommandDispatcher<ServerCommandSource> dispatcher) {
		LiteralArgumentBuilder<ServerCommandSource> root = CommandManager
				.literal("prefix")
				.requires(ctx -> {
					if (ctx.isExecutedByPlayer()) {
						return LP.hasPermission(ctx.getPlayer(), "suffixadvancements.base");
					} else {
						return true;
					}
				})
				.executes(this::run);

		LiteralCommandNode<ServerCommandSource> registeredCommand = dispatcher.register(root);

		dispatcher.register(CommandManager.literal("suffixadvancements").redirect(registeredCommand).executes(this::run));
		dispatcher.register(CommandManager.literal("suffixadv").redirect(registeredCommand).executes(this::run));

		registeredCommand.addChild(new ReloadCommand().build());
		registeredCommand.addChild(new AddCommand().build());
		registeredCommand.addChild(new TogglePrimordialCommand().build());
		registeredCommand.addChild(new CheckCommand().build());
		registeredCommand.addChild(new RemovePrefixCommand().build());
		registeredCommand.addChild(new RemoveChampion().build());
	}

	public int run(CommandContext<ServerCommandSource> context) {
		if (!context.getSource().isExecutedByPlayer()) {
			context.getSource().sendMessage(Text.literal("This command must be executed by a player."));
			return 1;
		}

		UIManager.openUIForcefully(context.getSource().getPlayer(),
				new SelectScreen().open(context.getSource().getPlayer(), true));

		return 1;
	}
}
