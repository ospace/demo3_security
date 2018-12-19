package com.example.demo3;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
//@Entity
public class UserRole {
//	@Id
//	@GeneratedValue
//	private Long id;
	
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
//	
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	public String toString() {
//		return "id["+id+"] "+super.toString();
//	}
}
