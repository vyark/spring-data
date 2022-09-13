package com.epam.dao;

import com.epam.model.Event;
import com.epam.model.User;
import com.epam.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.storage.Storage.EVENT_PREFIX_ID;
import static com.epam.storage.Storage.USER_PREFIX_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserDaoTest {
    @Mock
    private Storage storage;

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

        when(storage.getRepository()).thenReturn(new HashMap<>());

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

        when(storage.getRepository()).thenReturn(new HashMap<>());

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

        when(storage.getRepository()).thenReturn(repository);

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

        when(storage.getRepository()).thenReturn(repository);

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
    public void values(){
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");
        event.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        User user = new User();
        user.setId(1);
        user.setFirstName("Mary");
        user.setEmail("mary@mail.com");

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);
        repository.put(USER_PREFIX_ID + user.getId(), user);

        when(storage.getRepository()).thenReturn(repository);

        List<User> result = dao.values();

        assertEquals(1, result.size());
    }
}
