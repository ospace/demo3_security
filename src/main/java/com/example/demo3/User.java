package com.example.demo3;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	private String id;
	private String pwd;
	private List<UserRole> roles;
	
	public static User of(String id, String pwd, List<UserRole> roles) {
		User ret = new User();
		ret.setId(id);
		ret.setPwd(pwd);
		ret.setRoles(roles);
		return ret;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public List<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
	
	public String toString() {
		return String.format("id[%s] pwd[%s]", id, pwd);
	}
}


