package com.niit.joinme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.joinme.DAOImpl.EventDAOImpl;
import com.niit.joinme.model.Event;

@Service
@Transactional
public class EventService {

	@Autowired
    EventDAOImpl eventDAOImpl;
	
	public boolean save(Event event)
	{
		return eventDAOImpl.save(event);
	}
	
	public List<Event> listEvents() 
	{
		return eventDAOImpl.listEvents();
	}
	
	public Event getById(int eventId) 
	{
		return eventDAOImpl.getById(eventId);	
	}
	
	public void updateEvent(Event event) 
	{
		eventDAOImpl.updateEvent(event);
	}
	
	public void delete(int eventId)
	{
		eventDAOImpl.delete(eventId);
	}
}
