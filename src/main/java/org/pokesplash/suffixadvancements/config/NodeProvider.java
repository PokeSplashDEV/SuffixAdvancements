package org.pokesplash.suffixadvancements.config;

import net.luckperms.api.node.types.PrefixNode;
import net.luckperms.api.node.types.SuffixNode;
import org.pokesplash.suffixadvancements.SuffixAdvancements;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeProvider {
	private HashMap<AdvancementConfig, PrefixNode> nodes;
	private HashMap<String, PrefixNode> extraPrefix;
	private HashMap<String, SuffixNode> extraSuffix;
	private PrefixNode perfection;

	public NodeProvider() {
		nodes = new HashMap<>();
		extraPrefix = new HashMap<>();
		extraSuffix = new HashMap<>();
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

	public PrefixNode getExtraPrefix(String permission) {
		return extraPrefix.get(permission);
	}

	public SuffixNode getExtraSuffix(String permission) {
		return extraSuffix.get(permission);
	}

	public void init() {

		for (AdvancementConfig config : SuffixAdvancements.config.getConfigs()) {
			nodes.put(config,
					PrefixNode.builder(config.getSuffix(), 351).withContext("server", "cobblemon").build());
		}

		for (String permission : SuffixAdvancements.extras.getCustomPrefixes().keySet()) {

			String prefix = SuffixAdvancements.extras.getPrefix(permission);

			extraPrefix.put(permission,
					PrefixNode.builder(prefix, 351).withContext("server", "cobblemon").build());
		}

		for (String permission : SuffixAdvancements.extras.getCustomSuffixes().keySet()) {

			String suffix = SuffixAdvancements.extras.getSuffix(permission);

			extraSuffix.put(permission,
					SuffixNode.builder(suffix, 351).withContext("server", "cobblemon").build());
		}

		perfection = PrefixNode.builder(SuffixAdvancements.config.getPerfectionist(),
				351).build();
	}
}
