package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.VideoDao;
import com.shsh.ic.model.dto.Video;

@Service
public class VideoServiceImpl implements VideoService {

	private VideoDao videoDao;
	
	@Autowired
	public VideoServiceImpl(VideoDao videoDao) {
		this.videoDao = videoDao;
	}
	
	@Override
	public List<Video> selectVideosLatest() {
		return videoDao.selectVideosLatest();
	}

	@Override
	public List<Video> selectVideosPopular() {
		return videoDao.selectVideosPopular();
	}

	@Override
	public List<Video> selectSportsVideos(String category) {
		return videoDao.selectSportsVideos(category);
	}

	@Override
	public List<Video> selectHotVideos() {
		return videoDao.selectHotVideos();
	}

	@Override
	public boolean insertVideo(Video video) {
		return videoDao.insertVideo(video) > 0;
	}

	@Override
	public boolean deleteVideo(String videoId) {
		return videoDao.deleteVideo(videoId) > 0;
	}

	@Override
	public boolean updateViewCnt(String videoId) {
		return videoDao.updateViewCnt(videoId) > 0;
	}
}
