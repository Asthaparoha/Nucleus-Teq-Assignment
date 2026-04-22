package com.astha.todoapp.service;



import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
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