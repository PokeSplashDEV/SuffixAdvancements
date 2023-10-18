package org.pokesplash.suffixadvancements.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.util.LP;

public class TogglePrimordialCommand {
	public LiteralCommandNode<ServerCommandSource> build() {
		return CommandManager.literal("primordial")
				.requires(ctx -> {
					if (ctx.isExecutedByPlayer()) {
						return LP.hasPermission(ctx.getPlayer(), "suffixadvancements.admin");
					} else {
						return true;
					}
				})
				.executes(this::run)
				.build();
	}

	public int run(CommandContext<ServerCommandSource> context) {

		SuffixAdvancements.config.setPrimordialActive(!SuffixAdvancements.config.isPrimordialActive());

		context.getSource().sendMessage(Text.literal("Set Primordial to " + SuffixAdvancements.config.isPrimordialActive()));

		return 1;
	}
}
