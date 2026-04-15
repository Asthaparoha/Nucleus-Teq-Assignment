package com.example.session2.component;

import org.springframework.stereotype.Component;

@Component
public class ShortMessageFormatter implements MessageFormatter {

    public String format() {
        return "Short Message";
    }
}