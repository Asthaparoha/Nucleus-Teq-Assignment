package com.astha.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.astha.todoapp.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}