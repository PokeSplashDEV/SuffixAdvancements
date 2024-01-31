package org.pokesplash.suffixadvancements.command;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.util.LP;
import org.pokesplash.suffixadvancements.util.Perfectionist;

import java.util.ArrayList;

public class RemoveChampion {
	public LiteralCommandNode<ServerCommandSource> build() {
		return CommandManager.literal("removechampion")
				.requires(ctx -> {
					if (ctx.isExecutedByPlayer()) {
						return LP.hasPermission(ctx.getPlayer(), "suffixadvancements.admin");
					} else {
						return true;
					}
				})
				.executes(this::usage)
						.then(CommandManager.argument("player", StringArgumentType.string())
								.suggests((ctx, builder) -> {
									ArrayList<ServerPlayerEntity> players =
											new ArrayList<>(ctx.getSource().getServer().getPlayerManager().getPlayerList());

									for (ServerPlayerEntity player : players) {
										builder.suggest(player.getName().getString());
									}
									return builder.buildFuture();
								})
								.executes(this::run)

				)
				.build();
	}

	public int run(CommandContext<ServerCommandSource> context) {

		try {
			String player = StringArgumentType.getString(context, "player");

			if (!SuffixAdvancements.accounts.hasAccount(player)) {
				context.getSource().sendMessage(Text.literal("Could not find player with uuid " + player));
				return 1;
			}

			Account account = SuffixAdvancements.accounts.getAccount(player);

			if (account == null) {
				context.getSource().sendMessage(Text.literal("Account " + player + " returned null."));
				return 1;
			}

			account.getChampion().setComplete(false);
			SuffixAdvancements.accounts.updateAccount(account);

			context.getSource().sendMessage(Text.literal("Removed champion from " + player));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public int usage(CommandContext<ServerCommandSource> context) {
		context.getSource().sendMessage(Text.literal("/adv removechampion <player>"));
		return 1;
	}
}
