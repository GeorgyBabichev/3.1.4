package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService, UserDetailsService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImp(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Transactional
    public void updateUser(User user) {
        userRepository
                .findById(user.getId())
                .ifPresent(user1 -> {
                    user1.setName(user.getName());
                    user1.setLastName(user.getLastName());
                    user1.setAge(user.getAge());
                    user1.setLogin(user.getLogin());
                    user1.setRoles(user.getRoles());
                    if (user.getPassword().equals(user1.getPassword())) {
                        user1.setPassword(user.getPassword());
                    } else {
                        user1.setPassword(passwordEncoder.encode(user.getPassword()));
                    }
                    userRepository.save(user1);
                });
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User findUserByUserName(String name) {
        return userRepository.findByUsername(name);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        return user;
    }
}