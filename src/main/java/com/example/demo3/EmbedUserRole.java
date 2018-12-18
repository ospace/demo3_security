package com.example.demo3;

import javax.persistence.Embeddable;

@Embeddable
public class EmbedUserRole {
	private String name;

	public static EmbedUserRole of(String name) {
		EmbedUserRole role = new EmbedUserRole();
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
