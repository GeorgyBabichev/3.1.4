package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserServise {

    List<User> getAllUser();

    void addUser(User user);

    User findUserById(Long id);

    void deleteUserById(Long id);

    User findUserByUserName(String name);


}