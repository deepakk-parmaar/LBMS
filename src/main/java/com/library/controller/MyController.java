package com.library.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class MyController {

    // Method to handle GET requests for the /error endpoint
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String handleError() {
        // Your error handling logic here
        return "An error occurred!";
    }
}
