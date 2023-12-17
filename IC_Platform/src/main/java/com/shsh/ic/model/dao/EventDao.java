package com.shsh.ic.model.dao;

import java.util.List;

import com.shsh.ic.model.dto.Event;
import com.shsh.ic.model.dto.Player;

public interface EventDao {
	
	// 전체 일정 시간순
	public List<Event> selectAllEvents();
	
	// 해당 주의 일정 시간순
	public List<Event> selectEventsOfWeek(int week);
	
	// 선택 날짜 일정 시간순
	public List<Event> selectEventsOfDay(int month, int day);
	
	// 일정 상세보기
	public Event selectEventsDetail(int eventId);
	
	// 해당 일정 출전 선수
	public List<Player> selectEventPlayer(int eventId);
	
	// 일정 추가
	public int insertEvent(Event event);
	
	// 일정 수정
	public int updateEvent(Event event);
	
	// 일정 기대++
	public int updateEventExpect(int eventId);
	
	// 일정 삭제
	public int deleteEvent(int eventId);
	
	// 선택 날짜 일정 기대순
	public List<Event> selectMainEvents(int month, int day);
	
	// 메달 획득권 경기 시간순
	public List<Event> selectImportantEvents(int month, int day);
	
}
