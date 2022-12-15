package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServise;
import ru.kata.spring.boot_security.demo.service.UserServise;

import java.util.List;



@Controller()
@RequestMapping("admin")
public class AdminController {

    private final UserServise userServise;
    private final RoleServise roleServise;

    public AdminController(UserServise userServise, RoleServise roleServise) {
        this.userServise = userServise;
        this.roleServise = roleServise;
    }



    @GetMapping("")
    public String showAllUser(ModelMap model) {
        List<User> messages = userServise.getAllUser();
        model.addAttribute("messages", messages);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(ModelMap model) {

        model.addAttribute("messages", new User());
        List<Role> roles =  roleServise.getAllRole();
        model.addAttribute("roles", roles);

        return "userInfo";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("messages") User user) {

        userServise.addUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServise.deleteUserById(id);
        return "redirect:/admin";

    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, ModelMap model) {
        User messages = userServise.findUserById(id);
        model.addAttribute("messages", messages);
        List<Role> roles =  roleServise.getAllRole();
        model.addAttribute("roles", roles);
        return "userInfo";
    }


}