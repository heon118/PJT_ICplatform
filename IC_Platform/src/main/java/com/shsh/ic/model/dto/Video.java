package com.shsh.ic.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Video {
	private String videoId;
	private String videoTitle;
	private String category;
	private String uploadDate;
	private int videoCnt;
	
	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-mm-dd");
	
	public Video() {
	}

	public Video(String videoId, String videoTitle, String category, Date uploadDate, int videoCnt) {
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.category = category;
		this.uploadDate = simpleDate.format(uploadDate);
		this.videoCnt = videoCnt;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getVideoCnt() {
		return videoCnt;
	}

	public void setVideoCnt(int videoCnt) {
		this.videoCnt = videoCnt;
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", videoTitle=" + videoTitle + ", category=" + category + ", uploadDate="
				+ uploadDate + ", videoCnt=" + videoCnt + "]";
	}
}
