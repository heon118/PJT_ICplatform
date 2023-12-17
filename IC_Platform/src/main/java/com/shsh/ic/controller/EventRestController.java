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

import com.shsh.ic.model.dto.Event;
import com.shsh.ic.model.dto.Player;
import com.shsh.ic.model.service.EventService;

@RestController
@RequestMapping("/api")
public class EventRestController {

	private EventService eventService;

	@Autowired
	public EventRestController(EventService eventService) {
		this.eventService = eventService;
	}

	// 경기 일정 추가
	@PostMapping("/event")
	public ResponseEntity<?> addEvent(@RequestBody Event event) {
		if (eventService.insertEvent(event))
			return new ResponseEntity<String>("success", HttpStatus.CREATED);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 경기 일정 수정
	@PutMapping("/event")
	public ResponseEntity<?> modiEvent(@RequestBody Event event) {
		if (eventService.updateEvent(event))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 경기 일정 기대 추가
	@PutMapping("/event/{eventId}")
	public ResponseEntity<?> modiEventExpect(@PathVariable int eventId) {
		if (eventService.updateEventExpect(eventId))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 경기 일정 삭제
	@DeleteMapping("/event/{eventId}")
	public ResponseEntity<?> dropEvent(@PathVariable int eventId) {
		if (eventService.deleteEvent(eventId))
			return new ResponseEntity<String>("success", HttpStatus.OK);
		return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
	}

	// 전체 경기 일정
	@GetMapping("/event")
	public ResponseEntity<?> getAllEvents() {
		List<Event> eventList = eventService.selectAllEvents();
		if (eventList == null || eventList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}

	// 선택한 주의 일정 정렬
	@GetMapping("/event/week/{week}")
	public ResponseEntity<?> getEventsOfWeek(@PathVariable int week) {
		List<Event> eventList = eventService.selectEventsOfWeek(week);
		if (eventList == null || eventList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}

	// 선택한 날의 일정 정렬
	@GetMapping("/event/day/{month}/{day}")
	public ResponseEntity<?> getEventsOfDay(@PathVariable int month, @PathVariable int day) {
		List<Event> eventList = eventService.selectEventsOfDay(month, day);
		if (eventList == null || eventList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}

	// 일정 상세 보기
	@GetMapping("/event/{eventId}")
	public ResponseEntity<?> getEventDetail(@PathVariable int eventId) {
		Event event = eventService.selectEventsDetail(eventId);
		if (event == null)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	// 해당 일정 출전 선수
	@GetMapping("/event/players/{eventId}")
	public ResponseEntity<?> getEventPlayers(@PathVariable int eventId) {
		List<Player> playerList = eventService.selectEventPlayer(eventId);
		if (playerList == null || playerList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Player>>(playerList, HttpStatus.OK);
	}

	// 기대되는 일정 정렬
	@GetMapping("/event/main/{month}/{day}")
	public ResponseEntity<?> getMainEvents(@PathVariable int month, @PathVariable int day) {
		List<Event> eventList = eventService.selectMainEvents(month, day);
		if (eventList == null || eventList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}

	// 주요 일정 정렬(메달 획득권)
	@GetMapping("/event/important/{month}/{day}")
	public ResponseEntity<?> getImportantEvents(@PathVariable int month, @PathVariable int day) {
		List<Event> eventList = eventService.selectImportantEvents(month, day);
		if (eventList == null || eventList.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Event>>(eventList, HttpStatus.OK);
	}
}
