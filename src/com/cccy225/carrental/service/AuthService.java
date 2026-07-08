package com.cccy225.carrental.service;

import com.cccy225.carrental.auth.User;
import com.cccy225.carrental.persistence.UserFileRepository;
import com.cccy225.carrental.security.InputValidator;
import com.cccy225.carrental.security.PasswordHasher;

public class AuthService {
    private final UserFileRepository userRepository;

    public AuthService(UserFileRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String username, String password) {
        InputValidator.validateUsername(username);
        InputValidator.validatePassword(password);

        if (userRepository.findByUsername(username) != null) {
            throw new IllegalArgumentException("Username already exists. Please choose another username.");
        }

        String hashedPassword = PasswordHasher.hash(password);
        userRepository.save(new User(username.trim(), hashedPassword));
    }

    public User signIn(String username, String password) {
        InputValidator.validateUsername(username);
        InputValidator.validatePassword(password);

        User storedUser = userRepository.findByUsername(username);
        if (storedUser == null) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        String hashedPassword = PasswordHasher.hash(password);
        if (!storedUser.getPasswordHash().equals(hashedPassword)) {
            throw new IllegalArgumentException("Invalid username or password.");
        }

        return storedUser;
    }
}
