package com.astha.todoapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astha.todoapp.dto.TodoDTO;
import com.astha.todoapp.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    // 1. Create TODO
    @PostMapping
    public void create(@Valid @RequestBody TodoDTO dto) {
        service.createTodo(dto);
    }

    // 2. Get all TODOs
    @GetMapping
    public List<TodoDTO> getAll() {
        return service.getAllTodos();
    }

    // 3. Get TODO by ID
    @GetMapping("/{id}")
    public TodoDTO getById(@PathVariable Long id) {
        return service.getTodoById(id);
    }

    // 4. Update TODO
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody TodoDTO dto) {
        service.updateTodo(id, dto);
    }

    // 5. Delete TODO
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTodo(id);
    }
}