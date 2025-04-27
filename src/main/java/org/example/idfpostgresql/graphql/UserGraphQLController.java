package org.example.idfpostgresql.graphql;

import org.example.idfpostgresql.model.User;
import org.example.idfpostgresql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserGraphQLController {

    private final UserService userService;

    @Autowired
    public UserGraphQLController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> users() {
        List<User> users = userService.getAllUsers();
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @QueryMapping
    public User user(@Argument Integer id) {
        User user = userService.getUserById(id);
        // Don't return the password
        user.setPassword(null);
        return user;
    }

    @QueryMapping
    public User userBySerial(@Argument String serial) {
        Optional<User> userOpt = userService.getUserBySerial(serial);
        return userOpt.map(user -> {
            user.setPassword(null);
            return user;
        }).orElse(null);
    }

    @QueryMapping
    public List<User> usersByClassification(@Argument Long classification) {
        List<User> users = userService.getUsersByClassification(classification);
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @QueryMapping
    public List<User> searchUsersByLastName(@Argument String lastName) {
        List<User> users = userService.searchUsersByLastName(lastName);
        // Remove passwords from response
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    @MutationMapping
    public User createUser(
            @Argument Integer id,
            @Argument String firstName,
            @Argument String lastName,
            @Argument String serial,
            @Argument String password,
            @Argument Long classification) {

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSerial(serial);
        user.setPassword(password);
        user.setClassification(classification);

        User newUser = userService.createUser(user);
        // Don't return the password
        newUser.setPassword(null);
        return newUser;
    }

    @MutationMapping
    public User updateUser(
            @Argument Integer id,
            @Argument String firstName,
            @Argument String lastName,
            @Argument String serial,
            @Argument String password,
            @Argument Long classification) {

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSerial(serial);
        user.setPassword(password);
        user.setClassification(classification);

        User updatedUser = userService.updateUser(id, user);
        // Don't return the password
        updatedUser.setPassword(null);
        return updatedUser;
    }

    @MutationMapping
    public boolean deleteUser(@Argument Integer id) {
        try {
            userService.deleteUser(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @MutationMapping
    public Boolean authenticateUser(@Argument String serial, @Argument String password) {
        return userService.authenticateUser(serial, password);
    }
}