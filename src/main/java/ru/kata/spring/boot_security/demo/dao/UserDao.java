package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserDao {
    List<User> getAllUsers();
    User getById(int id);
    void saveUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User findByEmail(String email);
}
