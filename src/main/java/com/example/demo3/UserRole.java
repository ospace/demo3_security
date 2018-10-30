package com.example.demo3;

public class UserRole {
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return String.format("name[%s]", name);
	}
}
