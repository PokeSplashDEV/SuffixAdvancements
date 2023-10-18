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

public class AddCommand {
	public LiteralCommandNode<ServerCommandSource> build() {
		return CommandManager.literal("add")
				.requires(ctx -> {
					if (ctx.isExecutedByPlayer()) {
						return LP.hasPermission(ctx.getPlayer(), "suffixadvancements.add");
					} else {
						return true;
					}
				})
				.executes(this::usage)
				.then(CommandManager.argument("stat", StringArgumentType.string())
						.suggests((ctx, builder) -> {
							builder.suggest("ally");
							builder.suggest("onemore");
							return builder.buildFuture();
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
				)
				.build();
	}

	public int run(CommandContext<ServerCommandSource> context) {

		String argument = StringArgumentType.getString(context, "stat");
		String player = StringArgumentType.getString(context, "player");

		if (!SuffixAdvancements.accounts.hasAccount(player)) {
			context.getSource().sendMessage(Text.literal("Could not find player with uuid " + player));
			return 1;
		}

		Account account = SuffixAdvancements.accounts.getAccount(player);

		if (argument.equalsIgnoreCase("ally")) {
			account.getAlly().addCount();
			if (account.getAlly().getCount() >= SuffixAdvancements.config.getAlly().getValue()) {
				account.getAlly().setComplete(true);
			}
			SuffixAdvancements.accounts.updateAccount(account);
			Perfectionist.updatePerfectionist(account);
			return 1;
		}

		if (argument.equalsIgnoreCase("onemore")) {
			account.getOnemore().addCount();
			if (account.getOnemore().getCount() >= SuffixAdvancements.config.getOnemore().getValue()) {
				account.getOnemore().setComplete(true);
			}
			SuffixAdvancements.accounts.updateAccount(account);
			Perfectionist.updatePerfectionist(account);
			return 1;
		}

		context.getSource().sendMessage(Text.literal("This command only accepts \"ally\" or \"onemore\" as a " +
				"stat"));
		return 1;
	}

	public int usage(CommandContext<ServerCommandSource> context) {
		context.getSource().sendMessage(Text.literal("/adv add <stat> <uuid>"));
		return 1;
	}
}
