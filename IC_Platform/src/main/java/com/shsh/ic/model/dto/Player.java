package com.shsh.ic.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Player {
	private int id;
	private String name;
	private String gender;
	private String birth;
	private String sports;
	private String sportsDetail;
	private int gold;
	private int silver;
	private int bronze;
	private String intro;
	private int msgCnt;
	
	public Player() {
	}

	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-mm-dd");
	public Player(String name, String gender, Date birth, String sports, String sportsDetail, int gold, int silver,
			int bronze, String intro, int msgCnt) {
		this.name = name;
		this.gender = gender;
		this.birth = simpleDate.format(birth);
		this.sports = sports;
		this.sportsDetail = sportsDetail;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.intro = intro;
		this.msgCnt = msgCnt;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getSportsDetail() {
		return sportsDetail;
	}

	public void setSportsDetail(String sportsDetail) {
		this.sportsDetail = sportsDetail;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getMsgCnt() {
		return msgCnt;
	}

	public void setMsgCnt(int msgCnt) {
		this.msgCnt = msgCnt;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", gender=" + gender + ", birth=" + birth + ", sports=" + sports
				+ ", sportsDetail=" + sportsDetail + ", gold=" + gold + ", silver=" + silver + ", bronze=" + bronze
				+ ", intro=" + intro + ", msgCnt=" + msgCnt + "]";
	}
	
}
