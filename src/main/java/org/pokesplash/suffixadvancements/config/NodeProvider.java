package org.pokesplash.suffixadvancements.config;

import net.luckperms.api.context.ContextCalculator;
import net.luckperms.api.context.ImmutableContextSet;
import net.luckperms.api.node.metadata.NodeMetadataKey;
import net.luckperms.api.node.types.MetaNode;
import net.luckperms.api.node.types.SuffixNode;
import org.pokesplash.suffixadvancements.SuffixAdvancements;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeProvider {
	private HashMap<AdvancementConfig, SuffixNode> nodes;
	private SuffixNode perfection;

	public NodeProvider() {
		nodes = new HashMap<>();
		perfection = null;
	}

	public ArrayList<SuffixNode> getSuffixes() {
		return new ArrayList<>(nodes.values());
	}

	public SuffixNode getPerfection() {
		return perfection;
	}

	public SuffixNode getNode(AdvancementConfig property) {
		return nodes.get(property);
	}

	public void init() {

		for (AdvancementConfig config : SuffixAdvancements.config.getConfigs()) {
			nodes.put(config,
					SuffixNode.builder(" " + config.getSuffix(), 41).build());
		}

		perfection = SuffixNode.builder(SuffixAdvancements.config.getPerfectionist(),
				41).build();

	}
}
