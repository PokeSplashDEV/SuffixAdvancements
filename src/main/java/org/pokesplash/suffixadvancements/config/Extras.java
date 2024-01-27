package org.pokesplash.suffixadvancements.config;

import com.google.gson.Gson;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.util.Utils;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class Extras {

	private HashMap<String, String> customPrefixes;
	private HashMap<String, String> customSuffixes;

	public Extras() {
		customPrefixes = new HashMap<>();
		customSuffixes = new HashMap<>();
	}

	public String getPrefix(String permission) {
		return customPrefixes.get(permission);
	}

	public String getSuffix(String permission) {
		return customSuffixes.get(permission);
	}

	public void init() {
		CompletableFuture<Boolean> futureRead = Utils.readFileAsync("/config/suffixadvancements/",
				"extra.json", el -> {
					Gson gson = Utils.newGson();
					Extras cfg = gson.fromJson(el, Extras.class);
					customPrefixes = cfg.getCustomPrefixes();
					customSuffixes = cfg.getCustomSuffixes();
				});

		if (!futureRead.join()) {
			SuffixAdvancements.LOGGER.info("No extra.json file found for SuffixAdvancements. Attempting to generate " +
					"one");
			Gson gson = Utils.newGson();
			String data = gson.toJson(this);
			CompletableFuture<Boolean> futureWrite = Utils.writeFileAsync("/config/suffixadvancements/",
					"extra.json", data);

			if (!futureWrite.join()) {
				SuffixAdvancements.LOGGER.fatal("Could not write extra for SuffixAdvancements.");
			}
			return;
		}
		SuffixAdvancements.LOGGER.info("SuffixAdvancements extra file read successfully");
	}

	public HashMap<String, String> getCustomPrefixes() {
		return customPrefixes;
	}

	public HashMap<String, String> getCustomSuffixes() {
		return customSuffixes;
	}
}
