package com.example.demo3.entity;

import javax.persistence.Embeddable;

@Embeddable
public class UserRole {
	private String name;

	public static UserRole of(String name) {
		UserRole role = new UserRole();
		role.setName(name);
		return role;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return "name["+name+"]";
	}
}