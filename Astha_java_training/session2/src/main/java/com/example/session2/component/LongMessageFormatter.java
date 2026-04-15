package com.example.session2.component;

import org.springframework.stereotype.Component;

@Component
public class LongMessageFormatter implements MessageFormatter {

    public String format() {
        return "This is a detailed long message";
    }
}
