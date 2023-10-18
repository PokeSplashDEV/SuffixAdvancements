package org.pokesplash.suffixadvancements.command;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.util.LP;
import org.pokesplash.suffixadvancements.util.Utils;

public class CheckCommand {
	public LiteralCommandNode<ServerCommandSource> build() {
		return CommandManager.literal("check")
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

		Utils.checkAllStats();

		context.getSource().sendMessage(Text.literal("All Accounts have been checked and updated."));

		return 1;
	}
}
