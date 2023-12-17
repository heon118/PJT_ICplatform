package com.shsh.ic.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsh.ic.model.dao.EventDao;
import com.shsh.ic.model.dto.Event;
import com.shsh.ic.model.dto.Player;

@Service
public class EventServiceImpl implements EventService {
	
	private EventDao eventDao;
	
	@Autowired
	public EventServiceImpl(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public List<Event> selectAllEvents() {
		return eventDao.selectAllEvents();
	}
	
	@Override
	public List<Event> selectEventsOfWeek(int week) {
		return eventDao.selectEventsOfWeek(week);
	}

	@Override
	public List<Event> selectEventsOfDay(int month, int day) {
		return eventDao.selectEventsOfDay(month, day);
	}

	@Override
	public Event selectEventsDetail(int eventId) {
		return eventDao.selectEventsDetail(eventId);
	}
	
	@Override
	public List<Player> selectEventPlayer(int eventId) {
		return eventDao.selectEventPlayer(eventId);
	}

	@Override
	public boolean insertEvent(Event event) {
		return eventDao.insertEvent(event) > 0;
	}

	@Override
	public boolean updateEvent(Event event) {
		return eventDao.updateEvent(event) > 0;
	}
	
	@Override
	public boolean updateEventExpect(int eventId) {
		return eventDao.updateEventExpect(eventId) > 0;
	}

	@Override
	public boolean deleteEvent(int eventId) {
		return eventDao.deleteEvent(eventId) > 0;
	}

	@Override
	public List<Event> selectMainEvents(int month, int day) {
		return eventDao.selectMainEvents(month, day);
	}

	@Override
	public List<Event> selectImportantEvents(int month, int day) {
		return eventDao.selectImportantEvents(month, day);
	}

}
