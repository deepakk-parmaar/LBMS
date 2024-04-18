package com.library.controller;

import com.library.model.Users;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());

        return "register"; // Assuming "registrationForm" is the name of your registration form view
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user) {
        try {
            // Attempt to register the user
            userService.registerUser(user);
            return "redirect:/login?registered=true";
        } catch (RuntimeException e) {
            // Handle case where user already exists
            return "redirect:/register?error=userExists";
        }
    }
}
