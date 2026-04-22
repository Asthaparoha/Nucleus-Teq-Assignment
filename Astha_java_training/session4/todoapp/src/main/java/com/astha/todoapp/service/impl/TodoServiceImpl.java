package com.astha.todoapp.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.astha.todoapp.dto.TodoDTO;
import com.astha.todoapp.entity.Todo;
import com.astha.todoapp.entity.TodoStatus;
import com.astha.todoapp.repository.TodoRepository;
import com.astha.todoapp.service.NotificationServiceClient;
import com.astha.todoapp.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    private final TodoRepository repository;
    private final NotificationServiceClient notificationService;

    // ✅ Constructor Injection
    public TodoServiceImpl(TodoRepository repository, NotificationServiceClient notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public void createTodo(TodoDTO dto) {

        logger.info("Creating TODO in service layer");

        TodoStatus status = dto.getStatus() != null ? dto.getStatus() : TodoStatus.PENDING;

        Todo todo = new Todo(
                dto.getTitle(),
                dto.getDescription(),
                status,
                LocalDateTime.now()
        );

        repository.save(todo);

        // ✅ Dummy service call
        notificationService.sendNotification("New TODO created: " + dto.getTitle());

        logger.info("TODO created successfully");
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        logger.info("Fetching all TODOs");

        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDTO getTodoById(Long id) {

        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        return mapToDTO(todo);
    }

    @Override
    public void updateTodo(Long id, TodoDTO dto) {

        Todo todo = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        if (dto.getTitle() != null) {
            todo.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            todo.setDescription(dto.getDescription());
        }

        if (dto.getStatus() != null) {

            TodoStatus currentStatus = todo.getStatus();
            TodoStatus newStatus = dto.getStatus();

            if ((currentStatus == TodoStatus.PENDING && newStatus == TodoStatus.COMPLETED) ||
                (currentStatus == TodoStatus.COMPLETED && newStatus == TodoStatus.PENDING)) {

                todo.setStatus(newStatus);
            } else {
                throw new RuntimeException("Invalid status transition");
            }
        }

        repository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        repository.deleteById(id);
    }

    private TodoDTO mapToDTO(Todo todo) {
        TodoDTO dto = new TodoDTO();
        dto.setTitle(todo.getTitle());
        dto.setDescription(todo.getDescription());
        dto.setStatus(todo.getStatus());
        return dto;
    }
}