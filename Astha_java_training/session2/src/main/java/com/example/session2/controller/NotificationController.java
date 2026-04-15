package com.example.session2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.session2.component.NotificationComponent;

@RestController
public class NotificationController {

    private final NotificationComponent notificationComponent;

    public NotificationController(NotificationComponent notificationComponent) {
        this.notificationComponent = notificationComponent;
    }

    @GetMapping("/notify")
    public String notifyUser() {
        return notificationComponent.sendNotification();
    }
}
