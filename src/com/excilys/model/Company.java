package com.excilys.model;

import java.util.Objects;

public class Company {
	
	// ******* VARIABLES *******
	private int id;
	private String name;
	
	// ******* CONSTRUCTEURS *******
	public Company() { // Default Company constructor
		this.setId(-2);	// id=-2 => 2 missing parameters 
		this.setName("unkown Company");
	}

	public Company(int id) {
		Objects.requireNonNull(id);
		this.setId(id);
		this.setName("no name"); // A RAJOUTER : setName avec le nom correspondant à l'ID dans la DB 
	}
	
	public Company(String name) {
		Objects.requireNonNull(name);
		// A RAJOUTER : setId avec l'ID correspondant au nom dans la DB
		this.setId(-1);	// id=-1 => id is missing 
		this.setName(name);
	}
	
	public Company(int id, String name) {
		Objects.requireNonNull(id);
		this.setId(id);
		this.setName(name);
	}
	
	// ******* GETTERS *******
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	// ******* SETTERS *******
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	// ******* OTHER METHODS *******
	public String toString(){
		switch(this.getId()) {
		case -2: //Pas d'ID ni de nom
			return "This company is unrepertoried. This one has no name or ID.";
		case -1: //Pas d'ID 
			return "The company " +this.getName()+ " is unrepertoried. This one has no ID.";
		default:
			return "The company " +this.getName()+ " is repertoried with the ID n°" +this.getId();
		}
	}
}