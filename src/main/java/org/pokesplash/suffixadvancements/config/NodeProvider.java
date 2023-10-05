package org.pokesplash.suffixadvancements.config;

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
					SuffixNode.builder(config.getSuffix(), 150)
							.withContext("mod", "suffixadvancements").build());
		}

		perfection = SuffixNode.builder(SuffixAdvancements.config.getPerfectionist(),
				150).withContext("mod", "suffixadvancements").build();

	}
}