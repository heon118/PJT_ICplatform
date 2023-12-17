package com.shsh.ic.model.service;

import java.util.List;

import com.shsh.ic.model.dto.Video;

public interface VideoService {

	public List<Video> selectVideosLatest();
	
	public List<Video> selectVideosPopular();
	
	public List<Video> selectSportsVideos(String category);
	
	public List<Video> selectHotVideos();
	
	public boolean insertVideo(Video video);
	
	public boolean deleteVideo(String videoId);
	
	public boolean updateViewCnt(String videoId);
}
