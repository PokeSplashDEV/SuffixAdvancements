package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.event.node.NodeRemoveEvent;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.account.Account;
import org.pokesplash.suffixadvancements.config.NodeProvider;
import org.pokesplash.suffixadvancements.util.LP;
import org.pokesplash.suffixadvancements.util.Perfectionist;

public class LuckPermsEvent {
	public void registerEvent() {
		LuckPermsProvider.get().getEventBus().subscribe(NodeAddEvent.class, e -> {

			if (e.getNode().getValue()) {
				// WriteOff check.
				String writeOffNode = SuffixAdvancements.config.getWriteoff().getPermission();
				if (writeOffNode.equalsIgnoreCase(e.getNode().getKey())) {
					System.out.println(e.getTarget().getFriendlyName());
					Account account = SuffixAdvancements.accounts.getAccount(e.getTarget().getFriendlyName());
					account.getWriteoff().setComplete(true);
					Perfectionist.updatePerfectionist(account);
					SuffixAdvancements.accounts.updateAccount(account);
				}

				// camper check.
				String camperNode = SuffixAdvancements.config.getCamper().getPermission();
				if (camperNode.equalsIgnoreCase(e.getNode().getKey())) {
					System.out.println(e.getTarget().getFriendlyName());
					Account account = SuffixAdvancements.accounts.getAccount(e.getTarget().getFriendlyName());
					account.getCamper().setComplete(true);
					Perfectionist.updatePerfectionist(account);
					SuffixAdvancements.accounts.updateAccount(account);
				}

				// smurfNode check.
				String smurfNode = SuffixAdvancements.config.getSmurf().getPermission();
				if (smurfNode.equalsIgnoreCase(e.getNode().getKey())) {
					Account account = SuffixAdvancements.accounts.getAccount(e.getTarget().getFriendlyName());
					account.getSmurf().setComplete(true);
					Perfectionist.updatePerfectionist(account);
					SuffixAdvancements.accounts.updateAccount(account);
				}

				// champion check.
				String champNode = SuffixAdvancements.config.getChampion().getPermission();
				if (champNode.equalsIgnoreCase(e.getNode().getKey())) {
					Account account = SuffixAdvancements.accounts.getAccount(e.getTarget().getFriendlyName());
					account.getChampion().setComplete(true);
					SuffixAdvancements.accounts.updateAccount(account);
				}

				// stakeholder check.
				String stakeholderNode = SuffixAdvancements.config.getStakeholder().getPermission();
				if (stakeholderNode.equalsIgnoreCase(e.getNode().getKey())) {
					Account account = SuffixAdvancements.accounts.getAccount(e.getTarget().getFriendlyName());
					account.getStakeholder().setComplete(true);
					SuffixAdvancements.accounts.updateAccount(account);
				}
			}
		});
	}
}
