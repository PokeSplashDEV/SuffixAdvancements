package org.pokesplash.suffixadvancements.config;

import com.google.gson.Gson;
import org.pokesplash.suffixadvancements.SuffixAdvancements;
import org.pokesplash.suffixadvancements.util.Utils;

import java.util.concurrent.CompletableFuture;

public class Config {
	private CountConfig dealer;
	private CountConfig highroller;
	private CountConfig fortune;
	private PermissionConfig writeoff;
	private PermissionConfig camper;
	private CountConfig bountyhunter;
	private CountConfig unrivaled;
	private CountConfig bot;
	private CountConfig botanist;
	private CountConfig babyfactory;
	private CountConfig ally;
	private CountConfig toxic;
	private PermissionConfig smurf;
	private CountConfig lucky;
	private CountConfig habitue;
	private CountConfig onemore;
	private CountConfig liberator;
	private String perfectionist;
	private PermissionConfig champion;
	private PermissionConfig stakeholder;
	private PermissionConfig primordial;

	public Config() {
		dealer = new CountConfig();
		highroller = new CountConfig();
		fortune = new CountConfig();
		writeoff = new PermissionConfig();
		camper = new PermissionConfig();
		bountyhunter = new CountConfig();
		unrivaled = new CountConfig();
		bot = new CountConfig();
		botanist = new CountConfig();
		babyfactory = new CountConfig();
		ally = new CountConfig();
		toxic = new CountConfig();
		smurf = new PermissionConfig();
		lucky = new CountConfig();
		habitue = new CountConfig();
		onemore = new CountConfig();
		liberator = new CountConfig();
		perfectionist = "[Perfectionist]";
		champion = new PermissionConfig();
		stakeholder = new PermissionConfig();
		primordial = new PermissionConfig();
	}

	public CountConfig getDealer() {
		return dealer;
	}

	public CountConfig getHighroller() {
		return highroller;
	}

	public CountConfig getFortune() {
		return fortune;
	}

	public PermissionConfig getWriteoff() {
		return writeoff;
	}

	public PermissionConfig getCamper() {
		return camper;
	}

	public CountConfig getBountyhunter() {
		return bountyhunter;
	}

	public CountConfig getUnrivaled() {
		return unrivaled;
	}

	public CountConfig getBot() {
		return bot;
	}

	public CountConfig getBotanist() {
		return botanist;
	}

	public CountConfig getBabyfactory() {
		return babyfactory;
	}

	public CountConfig getAlly() {
		return ally;
	}

	public CountConfig getToxic() {
		return toxic;
	}

	public PermissionConfig getSmurf() {
		return smurf;
	}

	public CountConfig getLucky() {
		return lucky;
	}

	public CountConfig getHabitue() {
		return habitue;
	}

	public CountConfig getOnemore() {
		return onemore;
	}

	public CountConfig getLiberator() {
		return liberator;
	}

	public String getPerfectionist() {
		return perfectionist;
	}

	public PermissionConfig getChampion() {
		return champion;
	}

	public PermissionConfig getStakeholder() {
		return stakeholder;
	}

	public PermissionConfig getPrimordial() {
		return primordial;
	}

	public void init() {
		CompletableFuture<Boolean> futureRead = Utils.readFileAsync("/config/suffixadvancements/",
				"config.json", el -> {
					Gson gson = Utils.newGson();
					Config cfg = gson.fromJson(el, Config.class);
					dealer = cfg.getDealer();
					highroller = cfg.getHighroller();
					fortune = cfg.getFortune();
					writeoff = cfg.getWriteoff();
					camper = cfg.getCamper();
					bountyhunter = cfg.getBountyhunter();
					unrivaled = cfg.getUnrivaled();
					bot = cfg.getBot();
					botanist = cfg.getBotanist();
					babyfactory = cfg.getBabyfactory();
					ally = cfg.getAlly();
					toxic = cfg.getToxic();
					smurf = cfg.getSmurf();
					lucky = cfg.getLucky();
					habitue = cfg.getHabitue();
					onemore = cfg.getOnemore();
					liberator = cfg.getLiberator();
					perfectionist = cfg.getPerfectionist();
					champion = cfg.getChampion();
					stakeholder = cfg.getStakeholder();
					primordial = cfg.getPrimordial();
				});

		if (!futureRead.join()) {
			SuffixAdvancements.LOGGER.info("No config.json file found for SuffixAdvancements. Attempting to generate " +
					"one");
			Gson gson = Utils.newGson();
			String data = gson.toJson(this);
			CompletableFuture<Boolean> futureWrite = Utils.writeFileAsync("/config/suffixadvancements/",
					"config.json", data);

			if (!futureWrite.join()) {
				SuffixAdvancements.LOGGER.fatal("Could not write config for GTS.");
			}
			return;
		}
		SuffixAdvancements.LOGGER.info("SuffixAdvancements config file read successfully");
	}
}
