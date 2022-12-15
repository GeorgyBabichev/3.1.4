package ru.kata.spring.boot_security.demo;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;


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



    @Transactional
    @PostConstruct
    public void creationOfDefaultUsers() {
        var role = new Role(1L,"ROLE_ADMIN");
        var role2 = new Role(2L,"ROLE_USER");
        roleRepository.saveAll(Set.of(role, role2));
        saveUserInDataBase("Admin", 1970,"mail1", passwordEncoder.encode("1234"), List.of(role));
        saveUserInDataBase("User", 2000, "mail2",passwordEncoder.encode("1234"), List.of(role2));
    }

    @Transactional
    public void saveUserInDataBase(String name, int age,String mail, String password, List<Role> role) {
        User user = new User(name, age,mail, password, role);
        userRepository.save(user);
    }
}