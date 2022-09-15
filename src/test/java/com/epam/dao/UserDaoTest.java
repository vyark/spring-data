package com.epam.dao;

import com.epam.model.Event;
import com.epam.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.storage.Storage.EVENT_PREFIX_ID;
import static com.epam.storage.Storage.USER_PREFIX_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDaoTest {

    @InjectMocks
    private UserDao dao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPut() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Olga");
        user.setEmail("olga@mail.com");

        User result = dao.put(user.getId(), user);

        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void shouldReplace() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Olga");
        user.setEmail("olga@mail.com");

        User replaceUser = new User();
        replaceUser.setId(2);
        replaceUser.setFirstName("Tom");
        replaceUser.setEmail("tom@mail.com");

        dao.put(user.getId(), user);

        User result = dao.replace(replaceUser.getId(), replaceUser);

        assertEquals(replaceUser.getId(), result.getId());
    }

    @Test
    public void shouldGet() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Olga");
        user.setEmail("olga@mail.com");

        Map<String, Object> repository = new HashMap<>();
        repository.put(USER_PREFIX_ID + user.getId(), user);

        User result = dao.get(1);

        assertEquals(user.getId(), result.getId());
    }

    @Test
    public void shouldRemove_success() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Olga");
        user.setEmail("olga@mail.com");

        Map<String, Object> repository = new HashMap<>();
        repository.put(USER_PREFIX_ID + user.getId(), user);

        boolean result = dao.remove(1);

        assertEquals(true, result);
    }

    @Test
    public void shouldDeleteEvent_throwException() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                dao.remove(3));

        assertEquals("User doesn't exist with id: 3", thrown.getMessage());
    }

    @Test
    public void values() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");

        User user = new User();
        user.setId(1);
        user.setFirstName("Mary");
        user.setEmail("mary@mail.com");

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);
        repository.put(USER_PREFIX_ID + user.getId(), user);

        List<User> result = dao.values();

        assertEquals(1, result.size());
    }
}
