package com.shsh.ic.model.dto;

public class Country {
	private String name;
	private int gold;
	private int silver;
	private int bronze;
	private int totalMedal;
	
	public Country() {
	}

	public Country(String name, int gold, int silver, int bronze, int totalMedal) {
		this.name = name;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.totalMedal = totalMedal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getSilver() {
		return silver;
	}

	public void setSilver(int silver) {
		this.silver = silver;
	}

	public int getBronze() {
		return bronze;
	}

	public void setBronze(int bronze) {
		this.bronze = bronze;
	}

	public int getTotalMedal() {
		return totalMedal;
	}

	public void setTotalMedal(int totalMedal) {
		this.totalMedal = totalMedal;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", gold=" + gold + ", silver=" + silver + ", bronze=" + bronze
				+ ", totalMedal=" + totalMedal + "]";
	}
}
