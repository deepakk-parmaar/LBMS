package com.library.service;

import com.library.model.Users;

import java.util.List;

public interface UserService {
    void registerUser(Users user);

    Users getUserByEmail(String email);

    List<Users> getAllUsers();

    Users getUserById(Long userId);
}

