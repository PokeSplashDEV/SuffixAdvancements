package org.pokesplash.suffixadvancements.events;

import net.impactdev.impactor.api.economy.events.EconomyTransactionEvent;
import net.impactdev.impactor.api.events.ImpactorEventBus;

public class ImpactorEvent {
	public void registerEvent() {
		ImpactorEventBus.bus().subscribe(EconomyTransactionEvent.Post.class, (e) -> {
			System.out.println("Impactor transaction event heard");
		});
	}
}
