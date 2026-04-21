package com.astha.todoapp.service;

import java.util.List;

import com.astha.todoapp.dto.TodoDTO;

public interface TodoService {

    void createTodo(TodoDTO dto);

    List<TodoDTO> getAllTodos();

    TodoDTO getTodoById(Long id);

    void updateTodo(Long id, TodoDTO dto);

    void deleteTodo(Long id);
}