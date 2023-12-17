package com.shsh.ic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shsh.ic.model.dto.FanMsg;
import com.shsh.ic.model.service.FanMsgService;


@RestController
@RequestMapping("/api")
public class FanMsgRestController {
	
	private FanMsgService fanMsgService;
	
	@Autowired
	public FanMsgRestController(FanMsgService fanMsgService) {
		this.fanMsgService = fanMsgService;
	}
	
	// 팬이 응원 메시지 작성
	@PostMapping("/fanMsg/{playerId}")
	public ResponseEntity<?> addFanMsg(@RequestBody FanMsg fanMsg, @PathVariable int playerId){
		fanMsg.setPlayerId(playerId);
		if(fanMsgService.insertFanMsg(fanMsg))
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
	
	// 해당 선수의 팬 메시지 많은 거 20개
	@GetMapping("/fanMsg/{playerId}")
	public ResponseEntity<?> getFanMsg(@PathVariable int playerId){
		List<?> msgList = fanMsgService.selectFanMsgs(playerId);
		if(msgList == null || msgList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<?>>(msgList, HttpStatus.OK);
	}
	
	// 해당 선수의 팬 메시지 반환
	@GetMapping("/fanMsg/{playerId}/latest")
	public ResponseEntity<?> getFanMsgLatest(@PathVariable int playerId){
		List<FanMsg> msgList = fanMsgService.selectFanMsgsLatest(playerId);
		if(msgList == null || msgList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<FanMsg>>(msgList, HttpStatus.OK);
	}
	
	// 해당 선수의 팬 메시지 작성시 해당 선수의 메시지 개수 증가(인기도 증가)
	@PutMapping("/fanMsg/{playerId}")
	public ResponseEntity<?> addFanMsgCnt(@PathVariable int playerId){
		if(fanMsgService.updateFanMsgCnt(playerId))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}
}