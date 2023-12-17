package com.shsh.ic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shsh.ic.model.dto.PlayerMsg;
import com.shsh.ic.model.service.PlayerMsgService;

@RestController
@RequestMapping("/api")
public class PlayerMsgRestController {
	
	private PlayerMsgService playermsgservice;
	
	@Autowired
	public PlayerMsgRestController(PlayerMsgService playermsgservice) {
		this.playermsgservice = playermsgservice;
	}
	
	// 선수 메시지 작성
	@PostMapping("/playerMsg/{playerId}")
	public ResponseEntity<?> addPlayerMsg(@RequestBody PlayerMsg playermsg, @PathVariable int playerId){
		playermsg.setPlayerId(playerId);
		if(playermsgservice.insertPlayerMsg(playermsg))
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 해당 선수 작성 메시지 가져오기
	@GetMapping("/playerMsg/{playerId}")
	public ResponseEntity<?> getPlayerMsgs(@PathVariable int playerId){
		List<PlayerMsg> msgList = playermsgservice.selectPlayerMsg(playerId);
		if(msgList == null || msgList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<PlayerMsg>>(msgList, HttpStatus.OK);
	}
}
