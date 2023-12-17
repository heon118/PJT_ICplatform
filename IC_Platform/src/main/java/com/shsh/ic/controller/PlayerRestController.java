package com.shsh.ic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shsh.ic.model.dto.Player;
import com.shsh.ic.model.service.PlayerService;

@RestController
@RequestMapping("/api")
public class PlayerRestController {

	private PlayerService playerService;
	
	@Autowired
	public PlayerRestController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	// 전체 선수 가나다 순 가져오기
	@GetMapping("/player")
	public ResponseEntity<?> allPlayersName(){
		List<Player> playerList = playerService.selectAllPlayersName();
		
		if(playerList == null || playerList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
	}
	
	// 전체 선수 인기순 가져오기
	@GetMapping("/player/msgCnt")
	public ResponseEntity<?> allPlayersPopular(){
		List<Player> playerList = playerService.selectAllPlayersPopular();
		
		if(playerList == null || playerList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
	}
	
	// 종목별 선수 가져오기
	@GetMapping("/player/{sports}")
	public ResponseEntity<?> sportsPlayers(@PathVariable String sports){
		List<Player> playerList = playerService.selectSportsPlayers(sports);
		
		if(playerList == null || playerList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
	}
	
	// 오늘의 출전 선수
	@GetMapping("/player/today")
	public ResponseEntity<?> todayPlayers(){
		List<Player> playerList = playerService.selectTodayPlayers();
		if(playerList == null || playerList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
	}
	
	// 종목만 가져오기
	@GetMapping("/player/sports")
	public ResponseEntity<?> sportsPlayers(){
		List<String> sportsList = playerService.selectSports();
		
		if(sportsList == null || sportsList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<String>>(sportsList, HttpStatus.OK);
	}
	
	// 한 선수 가져오기
	@GetMapping("/player/detail/{id}")
	public ResponseEntity<?> playerDetail(@PathVariable int id){
		Player player = playerService.selectPlayerDetail(id);
		
		if(player == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	// 선수 상테메시지 수정
	@PutMapping("/player/{id}")
	public ResponseEntity<?> playerIntro(@RequestBody Player player, @PathVariable int id){
		player.setId(id);
		if(playerService.updatePlayerIntro(player))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 금메달 추가
	@PutMapping("/player/{id}/gold")
	public ResponseEntity<?> playerGold(@PathVariable int id){
		if(playerService.updatePlayerGold(id))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 은메달 추가
	@PutMapping("/player/{id}/silver")
	public ResponseEntity<?> playerSilver(@PathVariable int id){
		if(playerService.updatePlayerSilver(id))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 동메달 추가
	@PutMapping("/player/{id}/bronze")
	public ResponseEntity<?> playerBronze(@PathVariable int id){
		if(playerService.updatePlayerBronze(id))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}
