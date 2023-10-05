package org.pokesplash.suffixadvancements.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.SuffixAdvancements;

public class ReloadCommand {
	public LiteralCommandNode<ServerCommandSource> build() {
		return CommandManager.literal("reload")
				.requires(ctx -> {
					if (ctx.isExecutedByPlayer()) {
						// TODO check permissions
						return true;
					} else {
						return true;
					}
				})
				.executes(this::run)
				.build();
	}

	public int run(CommandContext<ServerCommandSource> context) {

		SuffixAdvancements.load();

		context.getSource().sendMessage(Text.literal("SuffixAdvancements reloaded."));

		return 1;
	}
}
