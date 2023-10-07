package org.pokesplash.suffixadvancements.config;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.node.metadata.NodeMetadataKey;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.node.types.SuffixNode;
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
					PrefixNode.builder(config.getSuffix(), 41).build());
		}

		perfection = PrefixNode.builder(SuffixAdvancements.config.getPerfectionist(),
				41).build();

	}
}
