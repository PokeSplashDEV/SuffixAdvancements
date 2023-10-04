package org.pokesplash.suffixadvancements.events.AdvancementEvent;

import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.node.NodeAddEvent;

public class LuckPermsEvent {
	public void registerEvent() {
		LuckPermsProvider.get().getEventBus().subscribe(NodeAddEvent.class, e -> {
			System.out.println("Node " + e.getNode().getKey() +
					" added to " + e.getTarget().getFriendlyName() +
					" as " + e.getNode().getValue());
		});
	}
}
