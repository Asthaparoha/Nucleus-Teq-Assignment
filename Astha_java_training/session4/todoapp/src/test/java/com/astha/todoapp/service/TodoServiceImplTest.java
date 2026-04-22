package com.astha.todoapp.service;

import com.astha.todoapp.dto.TodoDTO;
import com.astha.todoapp.entity.Todo;
import com.astha.todoapp.repository.TodoRepository;
import com.astha.todoapp.service.impl.TodoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TodoServiceImplTest {

    private final TodoRepository repository = mock(TodoRepository.class);
    private final NotificationServiceClient notificationService = mock(NotificationServiceClient.class);

    private final TodoServiceImpl service = new TodoServiceImpl(repository, notificationService);

    @Test
    void testCreateTodo() {
        TodoDTO dto = new TodoDTO();
        dto.setTitle("Test Task");

        service.createTodo(dto);

        verify(repository, times(1)).save(any(Todo.class));
        verify(notificationService, times(1)).sendNotification(anyString());
    }

    @Test
    void testGetTodoById() {
        Todo todo = new Todo();
        todo.setTitle("Test");

        when(repository.findById(1L)).thenReturn(Optional.of(todo));

        TodoDTO result = service.getTodoById(1L);

        assertEquals("Test", result.getTitle());
    }
}