package org.pokesplash.suffixadvancements.config;

import net.luckperms.api.node.types.PrefixNode;
import org.pokesplash.suffixadvancements.SuffixAdvancements;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeProvider {
	private HashMap<AdvancementConfig, PrefixNode> nodes;
	private PrefixNode perfection;

	public NodeProvider() {
		nodes = new HashMap<>();
		perfection = null;
	}

	public ArrayList<PrefixNode> getSuffixes() {
		return new ArrayList<>(nodes.values());
	}

	public PrefixNode getPerfection() {
		return perfection;
	}

	public PrefixNode getNode(AdvancementConfig property) {
		return nodes.get(property);
	}

	public void init() {

		for (AdvancementConfig config : SuffixAdvancements.config.getConfigs()) {
			nodes.put(config,
					PrefixNode.builder(config.getSuffix(), 351).withContext("server", "cobblemon").build());
		}

		perfection = PrefixNode.builder(SuffixAdvancements.config.getPerfectionist(),
				351).build();
	}
}
