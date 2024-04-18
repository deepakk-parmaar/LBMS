package com.library.controller;

import com.library.model.Users;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.library.UserSingleton;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSingleton userSingleton;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Assuming "login" is the name of your login form view
    }

    @GetMapping("/libraryuser/logout")
    public String logout() {
        // Clear the user ID stored in UserSingleton on logout
        userSingleton.setUserId(null);
        return "redirect:/login"; // Redirect to login page after logout
    }
    
    @GetMapping("/admin/logout")
    public String logout2() {
        // Clear the user ID stored in UserSingleton on logout
        userSingleton.setUserId(null);
        return "redirect:/login"; // Redirect to login page after logout
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {

        // Retrieve user by email
        Users user = userService.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Password matches, perform login logic

            // Store the user ID in UserSingleton
            userSingleton.setUserId(String.valueOf(user.getId())); // Convert the user ID to a String

            // Check if the user is an admin
            if (user.getEmail().equals("admin@gmail.com")) {
                redirectAttributes.addFlashAttribute("message", "Admin login successful!");
                return "redirect:/admin/"; // Redirect to admin dashboard
            } else {
                // Redirect to user dashboard
                return "redirect:/libraryuser/"; // Redirect to user dashboard
            }
        } else {
            // Password does not match or user not found, handle authentication failure
            redirectAttributes.addFlashAttribute("error", "Invalid email or password!");
            return "redirect:/login?error"; // Redirect back to login page with error message
        }
    }
}

