package com.event.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.beans.Event;
import com.event.repositories.EventRepository;
import com.event.repositories.UserRepository;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public List<Event> getAll(){
		System.out.println(eventRepo.findAll());
		return eventRepo.findAll();
	}
	
	public List<Event> getUpcoming(LocalDateTime dateTime) {
		LocalDateTime currTime = LocalDateTime.now();
		if(dateTime.isBefore(currTime)) return eventRepo.getByTime(dateTime);
		else return null;
	}
	
	public List<Event> getByVisible(boolean isVisable) {
		return eventRepo.getByVisible(true);
	}
	
	public Event getOne(int id) {
		return eventRepo.findOne(id);
	}
	
	public Event saveOne(Event e) {
		return eventRepo.save(e);
	}
	
	public void delete(Event e) {
		eventRepo.delete(e);
	}
	

	public void addUserToEvent(int userId, int eventId) {
		eventRepo.findOne(eventId).getUsers().add(userRepo.findOne(userId));
		List<Integer> toAdd = userRepo.findOne(userId).getEvents();
		toAdd.add(eventId);
		userRepo.findOne(userId).setEvents(toAdd);
		
	}
		
	public List<Event> getEventsFromCurrentTime(){
		return eventRepo.getByTimeGreaterThan(LocalDateTime.now());
	}
	
	public List<Event> getEventsByName(String name){
		return eventRepo.getByName(name);
	}
	
	public void addComment(int eventId, String comment)
	{
		eventRepo.findOne(eventId).getComments().add(comment);
	}
	
	public List<Event> search(String param){
		return eventRepo.getByName(param);
		//String[] keywords = param.split(param);
		//HashSet<Event> results = new HashSet<>();
		//for(String keyword : keywords) {
			//results.addAll(eventRepo.getByNameContains(keyword));
		//}
		//return results.stream().collect(Collectors.toList());
	}
	
}