package com.shsh.ic.model.dto;

import java.util.Date;

public class Posts {
	private int postId;
	private int playerId;
	private String postName;
	private String postPath;
	private long fileSize;
	private Date uploadDate;

	public Posts() {
	}

	public Posts(int playerId, String postName, String postPath, long fileSize) {
		super();
		this.playerId = playerId;
		this.postName = postName;
		this.postPath = postPath;
		this.fileSize = fileSize;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPostPath() {
		return postPath;
	}

	public void setPostPath(String postPath) {
		this.postPath = postPath;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public String toString() {
		return "Posts [postId=" + postId + ", playerId=" + playerId + ", postName=" + postName + ", postPath="
				+ postPath + ", fileSize=" + fileSize + ", uploadDate=" + uploadDate + "]";
	}

}
