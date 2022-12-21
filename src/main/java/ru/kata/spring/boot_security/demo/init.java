package ru.kata.spring.boot_security.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Component
public class init {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public init(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostConstruct
    private void postConstruct() {
        Role admin = new Role(1L, "ROLE_ADMIN");
        Role user = new Role(2L, "ROLE_USER");
        roleRepository.saveAll(List.of(admin, user));

        User adminUser = new User();
        adminUser.setName("Oleg");
        adminUser.setLastName("Petrov");
        adminUser.setAge((byte) 24);
        adminUser.setLogin("admin@mail.ru");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.addRole(admin);

        User normalUser = new User();
        normalUser.setName("Misha");
        normalUser.setLastName("Rudkov");
        normalUser.setAge((byte) 18);
        normalUser.setLogin("user@mail.ru");
        normalUser.setPassword(passwordEncoder.encode("user"));
        normalUser.addRole(user);

        userRepository.save(adminUser);
        userRepository.save(normalUser);
    }

}


