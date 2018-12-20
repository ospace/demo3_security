package com.example.demo3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="group_users")
public class GroupUser {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE) //다른 테이블과 시쿼스 공유
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	
	@ManyToOne
	private Group group;

	public static GroupUser of(String username, Group group) {
		GroupUser ret = new GroupUser();
		ret.setUsername(username);
		ret.setGroup(group);
		return ret;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
