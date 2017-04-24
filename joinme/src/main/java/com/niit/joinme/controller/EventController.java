package com.niit.joinme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.niit.joinme.model.Event;
import com.niit.joinme.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value= "/event", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Event event, UriComponentsBuilder builder)
	{

        boolean flag = eventService.save(event);
               if (flag == true) {
        	  return new ResponseEntity<Void>(HttpStatus.CONFLICT);
               }
               HttpHeaders headers = new HttpHeaders();
               headers.setLocation(builder.path("/event{eventId}").buildAndExpand(event.getEventId()).toUri());
               return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value= "/event", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> listEvents() {
		List<Event> EventList = eventService.listEvents();
		return new ResponseEntity<List<Event>>(EventList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/event/{eventId}", method = RequestMethod.GET )
	public ResponseEntity<Event> getById(@PathVariable("eventId") Integer eventId) {
		Event event = eventService.getById(eventId);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	@RequestMapping(value="/event/{eventId}", method = RequestMethod.PUT )
	public ResponseEntity<Event> updateBlog(@RequestBody Event event) {
		eventService.updateEvent(event);
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
	
	@RequestMapping(value="/event/{eventId}", method = RequestMethod.DELETE )
	public ResponseEntity<Void> delete(@PathVariable("eventId") Integer eventId) {
		eventService.delete(eventId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
}
