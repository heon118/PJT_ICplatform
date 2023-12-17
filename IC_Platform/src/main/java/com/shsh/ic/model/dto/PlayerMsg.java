package com.shsh.ic.model.dto;

import java.util.Date;

public class PlayerMsg {
	private int msgId;
	private int playerId;
	private String content;
	private Date uploadDate;
	
	public PlayerMsg() {
	}
	
	public PlayerMsg(int playerId, String content,Date uploadDate) {
		this.playerId = playerId;
		this.content = content;
		this.uploadDate = uploadDate;
	}

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
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

	@Override
	public String toString() {
		return "PlayerMsg [msgId=" + msgId + ", playerId=" + playerId + ", content=" + content + ", uploadDate="
				+ uploadDate + "]";
	}
	
}
