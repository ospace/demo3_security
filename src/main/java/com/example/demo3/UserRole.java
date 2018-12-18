package com.example.demo3;

import javax.persistence.Entity;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserRole extends EmbedUserRole {
	@Id
	@GeneratedValue
	private Long id;

	public static UserRole of(String name) {
		UserRole role = new UserRole();
		role.setName(name);
		return role;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String toString() {
		return "id["+id+"] "+super.toString();
	}
}
