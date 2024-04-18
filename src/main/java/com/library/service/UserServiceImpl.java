package com.library.service;

import com.library.model.Users;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Users> getAllUsers() {
        // Implement logic to retrieve all users from the repository
        return userRepository.findAll();
    }

    @Override
    public Users getUserByEmail(String email) {
        // Implement logic to retrieve a user by email from the repository
        return userRepository.findByEmail(email);
    }

    @Override
    public void registerUser(Users user) {
        // Check if user already exists
        Users existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }

        // Save user to the database
        userRepository.save(user);
    }

    @Override
    public Users getUserById(Long userId) {
        // Implement logic to retrieve a user by ID from the repository
        return userRepository.findById(userId).orElse(null);
    }

    // Other methods...
}
