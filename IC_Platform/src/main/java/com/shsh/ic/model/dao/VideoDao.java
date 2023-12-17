package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.Video;

public interface VideoDao {
	// 비디오 최신순 정렬
	public List<Video> selectVideosLatest();
	
	// 비디오 조회수순 정렬
	public List<Video> selectVideosPopular();
	
	// 선책한 종목의 비디오 출력
	public List<Video> selectSportsVideos(String category);
	
	// 최신 핫클립(최근 3일, 조회수 순)
	public List<Video> selectHotVideos();
	
	// 영상 추가
	public int insertVideo(Video video);
	
	// 영상 삭제
	public int deleteVideo(String videoId);
	
	// 영상 조회수 증가
	public int updateViewCnt(String videoId);
}
