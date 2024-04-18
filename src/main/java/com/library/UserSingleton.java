package com.library;

import org.springframework.stereotype.Component;

@Component
public class UserSingleton {
    private static UserSingleton instance;
    private String userId;

    // Private constructor to prevent instantiation from outside
    private UserSingleton() {
    }

    // Method to get the singleton instance
    public static synchronized UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    // Method to get the user ID
    public String getUserId() {
        return userId;
    }

    // Method to set the user ID
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
