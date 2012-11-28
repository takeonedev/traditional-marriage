package com.takeone.webapp;

public class Preference {

	private Person person;
	private Integer score;
	
	public Preference(Person people, Integer score) {
		this.person = people;
		this.score = score;
	}
	public Person getPeoson() {
		return person;
	}
	public void setPerson(Person people) {
		this.person = people;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}	
}
