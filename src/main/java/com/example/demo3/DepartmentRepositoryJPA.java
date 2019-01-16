package com.example.demo3;

//import java.util.List;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepositoryJPA extends CrudRepository<Department, Integer>{
//public interface UserRepositoryJPA extends JpaRepository<User, String>{	
//	List<User> findById(String name);
}
