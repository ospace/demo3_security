package com.example.demo3;

import org.springframework.data.repository.CrudRepository;

import com.example.demo3.entity.Group;

public interface GroupRepositoryJPA extends CrudRepository<Group, Integer> {

}
