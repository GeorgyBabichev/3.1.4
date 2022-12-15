package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServise;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServise userServise;

    public UserController(UserServise userServise) {
        this.userServise = userServise;
    }

    @GetMapping()
    public String getUser(Model model, Principal principal) {
        User messages = userServise.findByUser(principal.getName());
        model.addAttribute("messages", messages);
        return "user";
    }
}