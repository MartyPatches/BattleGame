package com.danielmeyer.battlerap;

import java.util.ArrayList;
import java.util.List;

public class BattleRapper {
	private static List<BattleRapper> brList = new ArrayList<BattleRapper>();

	private int id;
	private String name;
	private Skills skills = new Skills();
	private List<String> languages = new ArrayList<String>();
	private String hometown;

	public BattleRapper() {
		//InitBattleRappers();
	}

	public static void InitBattleRappers() {
		// Create battlerapper-object
		BattleRapper b = new BattleRapper();

		// Set id, name and hometown
		b.id = 1;
		b.name = "Henry Bowers";
		b.hometown = "Uppsala";

		// Add languages
		b.languages.add("sv");
		b.languages.add("en");
		b.languages.add("no");

		// Set skills
		b.skills.setConfidence(8);
		b.skills.setDelivery(10);
		b.skills.setExperience(7);
		b.skills.setFlow(4);
		b.skills.setHumor(8);
		b.skills.setPunchlines(7);
		b.skills.setShape(5);

		// Add br to brList
		brList.add(b);

		BattleRapper b2 = new BattleRapper();
		// Create battlerapper-object

		// Set id, name and hometown
		b2.id = 2;
		b2.name = "Mr Cool";
		b2.hometown = "Malmš";

		// Add languages
		b2.languages.clear();
		b2.languages.add("sv");
		b2.languages.add("en");

		// Set skills
		b2.skills.setConfidence(9);
		b2.skills.setDelivery(7);
		b2.skills.setExperience(8);
		b2.skills.setFlow(3);
		b2.skills.setHumor(10);
		b2.skills.setPunchlines(8);
		b2.skills.setShape(5);

		// Add br to brList
		brList.add(b2);

	}

	public static List<BattleRapper> getBrList() {
		return brList;
	}

	public static void setBrList(List<BattleRapper> brList) {
		BattleRapper.brList = brList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

}
