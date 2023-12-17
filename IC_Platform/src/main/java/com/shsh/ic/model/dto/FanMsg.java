package com.shsh.ic.model.dto;

import java.util.Date;

public class FanMsg {
	private int fanmsgId;
	private int playerId;
	private String nickname;
	private String content;
	private Date uploadDate;

	public FanMsg() {
	}

	public FanMsg(int playerId, String nickname, String content, Date uploadDate) {
		this.playerId = playerId;
		this.nickname = nickname;
		this.content = content;
		this.uploadDate = uploadDate;
	}

	public int getFanmsgId() {
		return fanmsgId;
	}

	public void setFanmsgId(int fanmsgId) {
		this.fanmsgId = fanmsgId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "FanMsg [fanmsgId=" + fanmsgId + ", playerId=" + playerId + ", nickname=" + nickname + ", content="
				+ content + ", uploadDate=" + uploadDate + "]";
	}
}
