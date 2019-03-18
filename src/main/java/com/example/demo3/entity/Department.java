package com.example.demo3.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String  name;
	
	//@OneToMany(mappedBy="department")
	@OneToMany
	@JoinColumn(name = "dept_id")
	@OrderBy("name ASC")
	/*
	 * 그냥 OneToMany를 사용하면 n:m관계로 중간에 매핑테이블이 생성됨
	 * 그렇지 않으려면 부모엔티티에 ManyToOne을 추가하고 mappedBy에 부모에 추가한 필드명을 입력
	 * 또는 JoinColumn으로 자식 엔티티의 ID가 부모의 외례키 조인 컬럼명을 지정하면 됨 
	 */
	private List<User> users;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
