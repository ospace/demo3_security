package com.example.demo3;

import org.springframework.data.repository.CrudRepository;

import com.example.demo3.entity.GroupUser;

public interface GroupUserRepositoryJPA extends CrudRepository<GroupUser, Integer> {
}
