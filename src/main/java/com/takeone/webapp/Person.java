package com.takeone.webapp;

import java.util.Queue;



public class Person implements Comparable<Person> {

	private Long id;
	private String name;
	private Gender gender;
	private Queue<Preference> preferences;
	private Preference preference;
	
	public Person(Long id, String name, Gender gender) {
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Queue<Preference> getPreferences() {
		return preferences;
	}
	public void setPreferences(Queue<Preference> preferences) {
		this.preferences = preferences;
	}
	public Preference getPreference() {
		return preference;
	}
	public void setPreference(Preference preference) {
		this.preference = preference;
	}
	public int compareTo(Person o) {	
		if(this.id < o.getId()) {
			return -1;
		} else if(o.getId() < this.id) {
			return 1;
		} else {
			return 0;
		}
	}		
}
