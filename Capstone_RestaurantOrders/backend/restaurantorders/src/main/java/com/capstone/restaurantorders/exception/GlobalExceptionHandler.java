package com.capstone.restaurantorders.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 1. Validation Errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return errors;
    }

    // 🔴 2. Business Logic Errors (like duplicate email)
    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeException(RuntimeException ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return error;
    }

    // 🔴 3. Generic Exception (fallback - A-grade touch)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleGenericException(Exception ex) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "Something went wrong. Please try again.");

        return error;
    }
}