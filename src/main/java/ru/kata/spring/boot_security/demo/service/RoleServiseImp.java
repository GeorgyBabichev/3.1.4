package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
public class RoleServiseImp implements RoleServise {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiseImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

@Override
    public List<Role> getAllRole(){
        return roleRepository.findAll();
}
}