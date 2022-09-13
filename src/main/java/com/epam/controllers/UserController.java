package com.epam.controllers;

import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ModelAndView getUsers() {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("users", userService.getUsers());
        return modelAndView;
    }

    @GetMapping("/user/id/{id}")
    public ModelAndView getUserById(@PathVariable(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("users",
                Arrays.asList(userService.getUserById(Long.parseLong(id))));
        return modelAndView;
    }

    @GetMapping("/user/email/{email}")
    public ModelAndView getUserByEmail(@PathVariable(name = "email") String email) {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("users",
                Arrays.asList(userService.getUserByEmail(email)));
        return modelAndView;
    }

    @GetMapping("/event/name/{name}")
    public ModelAndView getUsersByName(@PathVariable(name = "name") String name) {
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("users", userService.getUsersByName(name, 5, 1));
        return modelAndView;
    }
}
