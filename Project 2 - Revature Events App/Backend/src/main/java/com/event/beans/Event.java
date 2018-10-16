package com.event.beans;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="EVENT_TABLE")
public class Event {

	@Id
	@SequenceGenerator(name="event_seq_gen", sequenceName="event_seq", allocationSize=1)
	@GeneratedValue(generator="event_seq_gen", strategy=GenerationType.SEQUENCE)
	private int id;
	
	private String name;
	private String description;
	private boolean visible;
	private String notification;
	private String location;
	
	@ManyToMany(targetEntity=User.class, fetch=FetchType.EAGER)
	@JoinTable(name="events_to_users", joinColumns=@JoinColumn(name="event_id"),
	inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<User> users;
	
	@ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
	private Set<String> tags;
	
	@ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
	private Set<String> groups;
	private LocalDateTime time;
	
	@ElementCollection(targetClass=String.class, fetch=FetchType.EAGER)
	private Set<String> comments;
	
	public Event() {}
	
	public Event(String name, String description, LocalDateTime time) {
		super();
		this.name = name;
		this.description = description;
		this.time = time;
	}

	public Event(String name, String description, Set<User> users, Set<String> tags, Set<String> groups,
			LocalDateTime time) {
		super();
		this.name = name;
		this.description = description;
		this.users = users;
		this.tags = tags;
		this.groups = groups;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<String> getGroups() {
		return groups;
	}

	public void setGroups(Set<String> groups) {
		this.groups = groups;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", name=" + name + ", description=" + description + ", users=" + users + ", tags="
				+ tags + ", groups=" + groups + ", time=" + time + "]";
	}
	
	public Set<String> getComments() {
		return comments;
	}

	public void setComments(Set<String> comments) {
		this.comments = comments;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}