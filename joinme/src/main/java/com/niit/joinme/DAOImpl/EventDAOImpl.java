package com.niit.joinme.DAOImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.joinme.DAO.EventDAO;
import com.niit.joinme.model.Event;
@Repository
public class EventDAOImpl implements EventDAO{

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(Event event) {
		sessionFactory.getCurrentSession().save(event);
		return false;

	}
	@SuppressWarnings("unchecked")
	public List<Event> listEvents() {
		// TODO Auto-generated method stub
		List<Event> list = sessionFactory.getCurrentSession().createQuery("from Event").getResultList();
		return list;

	}
	@SuppressWarnings("unchecked")
	public Event getById(int eventId) {
		String hql = "from Event where eventId = "+eventId;
		List<Event> list = this.sessionFactory.getCurrentSession().createQuery(hql).getResultList();
		if(list != null && !list.isEmpty())
		{
			return list.get(0);
		}else{
			return null;
		}

	}

	public void updateEvent(Event event) {
		Event e = getById(event.getEventId());
		e.setEventName(event.getEventName());
		e.setEventDate(event.getEventDate());
		e.setEventContent(event.getEventContent());
		sessionFactory.getCurrentSession().update(e);

		
	}

	public void delete(int eventId) {
		Event event = new Event();
		event.setEventId(eventId);
		sessionFactory.getCurrentSession().delete(event);
		
	}

}
