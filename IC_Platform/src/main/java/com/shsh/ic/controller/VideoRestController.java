package com.shsh.ic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shsh.ic.model.dto.Video;
import com.shsh.ic.model.service.VideoService;

@RestController
@RequestMapping("api/")
public class VideoRestController {

	private VideoService videoService;

	@Autowired
	public VideoRestController(VideoService videoService) {
		this.videoService = videoService;
	}

	// 비디오 최신순 정렬
	@GetMapping("/video/latest")
	public ResponseEntity<?> getVideoLatest() {
		List<Video> videoList = videoService.selectVideosLatest();
		if (videoList == null || videoList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}

	// 비디오 조회수순 정렬
	@GetMapping("/video/popular")
	public ResponseEntity<?> getVideoPopular() {
		List<Video> videoList = videoService.selectVideosPopular();
		if (videoList == null || videoList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}

	// 선택 종목의 비디오 출력
	@GetMapping("/video/{category}")
	public ResponseEntity<?> getSportsVideos(@PathVariable String category) {
		List<Video> videoList = videoService.selectSportsVideos(category);
		if (videoList == null || videoList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}


	// 비디오 조회수순 정렬
	@GetMapping("/video/hot")
	public ResponseEntity<?> getHotVideos() {
		List<Video> videoList = videoService.selectHotVideos();
		if (videoList == null || videoList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
	}
	
	// 비디오 등록
	@PostMapping("/video")
	public ResponseEntity<?> addVideo(@RequestBody Video video){
		if(videoService.insertVideo(video))
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 비디오 삭제
	@DeleteMapping("/video/{videoId}")
	public ResponseEntity<?> dropVideo(@PathVariable String videoId){
		if(videoService.deleteVideo(videoId))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail",HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/video/{videoId}")
	public ResponseEntity<?> plusViewCnt(@PathVariable String videoId){
		if(videoService.updateViewCnt(videoId))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
