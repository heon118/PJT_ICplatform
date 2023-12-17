package com.shsh.ic.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private int eventId;
	private int playerId;
	private String eventDate;
	private String content;
	private String result;
	private int expect;
	private int medalChance;
	
	public Event() {
	}
	
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-mm-dd");

	public Event(int eventId, int playerId, Date eventDate, String content, int medalChance) {
		this.eventId = eventId;
		this.playerId = playerId;
		this.eventDate = simpleDate.format(eventDate);
		this.content = content;
		this.medalChance = medalChance;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getExpect() {
		return expect;
	}

	public void setExpect(int expect) {
		this.expect = expect;
	}

	public int getMedalChance() {
		return medalChance;
	}

	public void setMedalChance(int medalChance) {
		this.medalChance = medalChance;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", playerId=" + playerId + ", eventDate=" + eventDate + ", content="
				+ content + ", result=" + result + ", expect=" + expect + ", medalChance=" + medalChance + "]";
	}
	
}
