package com.shsh.ic.model.service;

import java.util.List;

import com.shsh.ic.model.dto.Event;
import com.shsh.ic.model.dto.Player;

public interface EventService {

	public List<Event> selectAllEvents();
	
	public List<Event> selectEventsOfWeek(int week);
	
	public List<Event> selectEventsOfDay(int month, int day);
	
	public Event selectEventsDetail(int eventId);
	
	public List<Player> selectEventPlayer(int eventId);
	
	public boolean insertEvent(Event event);
	
	public boolean updateEvent(Event event);
	
	public boolean updateEventExpect(int eventId);
	
	public boolean deleteEvent(int eventId);
	
	public List<Event> selectMainEvents(int month, int day);
	
	public List<Event> selectImportantEvents(int month, int day);
}
