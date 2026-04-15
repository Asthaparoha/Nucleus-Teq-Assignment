package com.example.session2.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.session2.component.NotificationComponent;
import com.example.session2.exception.UserNotFoundException;
import com.example.session2.model.User;
import com.example.session2.repository.UserRepository;
@Service
public class UserService {

    private final UserRepository userRepository;
    private final NotificationComponent notificationComponent;
    public UserService(UserRepository userRepository,
                   NotificationComponent notificationComponent) {
    this.userRepository = userRepository;
    this.notificationComponent = notificationComponent;
}

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(int id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
           throw new UserNotFoundException("User not found with id: " + id);
        }
        return user;
    }

    public void createUser(User user) {
    userRepository.saveUser(user);
    notificationComponent.sendNotification();
}}
