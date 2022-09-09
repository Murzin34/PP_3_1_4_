package ru.kata.spring.boot_security.demo.configs;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;


@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleService.saveRole(role1);
        roleService.saveRole(role2);

        User user = new User();
        user.setName("User1");
        user.setSurname("User1");
        user.setEmail("User1@mail.ru");
        user.setPassword("user1");
        user.addRole(role2);

        User user2 = new User();
        user2.setName("Admin");
        user2.setSurname("Admin");
        user2.setEmail("Admin@mail.ru");
        user2.setPassword("admin");
        user2.addRole(role1);

        User user3 = new User();
        user3.setName("Ivan");
        user3.setSurname("Ivanov");
        user3.setEmail("Ivanov@mail.ru");
        user3.setPassword("ivan");
        user3.addRole(role1);
        user3.addRole(role2);

        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
