package com.example.session2.service;

import org.springframework.stereotype.Service;

import com.example.session2.component.LongMessageFormatter;
import com.example.session2.component.ShortMessageFormatter;

@Service
public class MessageService {

    private final ShortMessageFormatter shortFormatter;
    private final LongMessageFormatter longFormatter;

    public MessageService(ShortMessageFormatter shortFormatter,
                          LongMessageFormatter longFormatter) {
        this.shortFormatter = shortFormatter;
        this.longFormatter = longFormatter;
    }

    public String getMessage(String type) {

        if ("SHORT".equalsIgnoreCase(type)) {
            return shortFormatter.format();
        } else {
            return longFormatter.format();
        }
    }
}