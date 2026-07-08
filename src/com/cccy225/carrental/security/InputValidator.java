package com.cccy225.carrental.security;

import com.cccy225.carrental.model.TripRequest;

public final class InputValidator {
    private InputValidator() {
    }

    public static void validate(TripRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Trip request cannot be null.");
        }
        if (request.getPassengerCount() <= 0) {
            throw new IllegalArgumentException("Passenger count must be greater than zero.");
        }
        if (request.getRentalDays() <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than zero.");
        }
        if (request.getTripMileage() <= 0) {
            throw new IllegalArgumentException("Trip mileage must be greater than zero.");
        }
        if (request.getPassengerCount() > 7) {
            throw new IllegalArgumentException("No car in the system supports more than 7 passengers.");
        }
        if (request.getRentalDays() > 365) {
            throw new IllegalArgumentException("Rental days exceed the supported range.");
        }
        if (request.getTripMileage() > 100000) {
            throw new IllegalArgumentException("Trip mileage exceeds the supported range.");
        }
    }

    public static void validateUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        String trimmed = username.trim();
        if (trimmed.length() < 3 || trimmed.length() > 20) {
            throw new IllegalArgumentException("Username must be between 3 and 20 characters.");
        }
        if (!trimmed.matches("[A-Za-z0-9_]+")) {
            throw new IllegalArgumentException("Username may contain only letters, numbers, and underscore.");
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long.");
        }
    }
}
