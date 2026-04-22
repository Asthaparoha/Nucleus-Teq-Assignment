package com.astha.todoapp.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    public TodoController(TodoService service) {
        this.service = service;
    }

    // 1. Create TODO

@PostMapping
public TodoDTO create(@Valid @RequestBody TodoDTO dto) {
   logger.info("Creating new TODO with title: {}", dto.getTitle());
    service.createTodo(dto);
    return dto;
}

    // 2. Get all TODOs
    @GetMapping
    public List<TodoDTO> getAll() {
        logger.info("Fetching all TODOs");
        return service.getAllTodos();
}

    // 3. Get TODO by ID
    @GetMapping("/{id}")
    public TodoDTO getById(@PathVariable Long id) {
        return service.getTodoById(id);
    }

    // 4. Update TODO
    @PutMapping("/{id}")
public TodoDTO update(@PathVariable Long id, @RequestBody TodoDTO dto) {
    service.updateTodo(id, dto);
    return dto;
}

    // 5. Delete TODO
    @DeleteMapping("/{id}")
public String delete(@PathVariable Long id) {
    service.deleteTodo(id);
    return "Todo deleted successfully";
}
}