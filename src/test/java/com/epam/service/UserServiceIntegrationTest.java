package com.epam.service;

import com.epam.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService service;

    @Test
    public void shouldGetUserById() {
        User result = service.getUserById(1);

        assertEquals(1, result.getId());
    }

    @Test
    public void shouldGetUserByEmail_success() {
        User result = service.getUserByEmail("john@mail.com");

        assertEquals("john@mail.com", result.getEmail());
    }

    @Test
    public void shouldGetUserByEmail_throwException() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                service.getUserByEmail("tom@mail.com"));

        assertEquals("User does not exist with email: tom@mail.com", thrown.getMessage());

    }

    @Test
    public void shouldGetUsersByName() {
        List<User> result = service.getUsersByName("John", 5, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setId(3);
        user.setFirstName("Alice");
        user.setEmail("alice@mail.com");

        User result = service.createUser(user);

        assertEquals(3, result.getId());
    }

    @Test
    public void shouldUpdateUser() {
        User user = new User();
        user.setId(2);
        user.setFirstName("Peter");
        user.setEmail("peter@mail.com");

        User result = service.updateUser(user);

        assertEquals("Peter", result.getFirstName());
        assertEquals("peter@mail.com", result.getEmail());
    }

    @Test
    public void shouldDeleteUser_success() {
        boolean result = service.deleteUser(2);

        assertEquals(true, result);
    }

    @Test
    public void shouldDeleteUser_throwException() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                service.deleteUser(55));

        Assertions.assertEquals("User doesn't exist with id: 55", thrown.getMessage());
    }
}
