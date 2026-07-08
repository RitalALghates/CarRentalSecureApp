package com.cccy225.carrental.persistence;

import com.cccy225.carrental.auth.User;
import com.cccy225.carrental.security.RecordIntegrityProtector;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class UserFileRepository {
    private final Path filePath;

    public UserFileRepository() {
        this(Paths.get("data", "users.txt"));
    }

    public UserFileRepository(Path filePath) {
        this.filePath = filePath;
        ensureFileExists();
        normalizeLegacyRecords();
    }

    public List<User> findAll() {
        ensureFileExists();
        List<User> users = new ArrayList<User>();
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }

                String[] parts = trimmed.split(":", 3);
                if (parts.length != 3) {
                    throw new IllegalStateException("User data integrity validation failed.");
                }

                String username = parts[0];
                String passwordHash = parts[1];
                String recordChecksum = parts[2];

                if (!RecordIntegrityProtector.matches(recordChecksum, username, passwordHash)) {
                    throw new IllegalStateException("User data integrity validation failed.");
                }

                users.add(new User(username, passwordHash));
            }
            return users;
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to read the user data file.", ex);
        }
    }

    public User findByUsername(String username) {
        for (User user : findAll()) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public void save(User user) {
        ensureFileExists();
        String checksum = RecordIntegrityProtector.createChecksum(user.getUsername(), user.getPasswordHash());
        String line = user.getUsername() + ":" + user.getPasswordHash() + ":" + checksum + System.lineSeparator();
        try {
            Files.write(filePath, line.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to save the user data file.", ex);
        }
    }

    private void ensureFileExists() {
        try {
            Path parent = filePath.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to prepare the user data file.", ex);
        }
    }

    private void normalizeLegacyRecords() {
        try {
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
            List<String> normalized = new ArrayList<String>();
            boolean changed = false;

            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    continue;
                }

                String[] twoPart = trimmed.split(":", 2);
                String[] threePart = trimmed.split(":", 3);

                if (threePart.length == 3) {
                    normalized.add(trimmed);
                    continue;
                }

                if (twoPart.length == 2) {
                    String checksum = RecordIntegrityProtector.createChecksum(twoPart[0], twoPart[1]);
                    normalized.add(twoPart[0] + ":" + twoPart[1] + ":" + checksum);
                    changed = true;
                } else {
                    throw new IllegalStateException("User data file contains an invalid record.");
                }
            }

            if (changed) {
                Files.write(filePath, normalized, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to normalize the user data file.", ex);
        }
    }
}
