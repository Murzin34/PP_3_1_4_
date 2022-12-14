package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getRoles().size() == 0) {
            user.addRole(roleDao.findRoleById(2));
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userDao.saveUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        if (updatedUser.getRoles().size() == 0) {
            updatedUser.addRole(roleDao.findRoleById(2));
        }
        String encryptedPassword = passwordEncoder.encode(updatedUser.getPassword());
        updatedUser.setPassword(encryptedPassword);
        userDao.updateUser(updatedUser);
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }
}
