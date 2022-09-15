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

public class EventDaoTest {
    @InjectMocks
    private EventDao dao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPut() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");

        Event result = dao.put(event.getId(), event);

        assertEquals(event.getId(), result.getId());
    }

    @Test
    public void shouldReplace() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");

        Event replaceEvent = new Event();
        replaceEvent.setId(2);
        replaceEvent.setTitle("Pop Music");

        dao.put(event.getId(), event);

        Event result = dao.replace(replaceEvent.getId(), replaceEvent);

        assertEquals(replaceEvent.getId(), result.getId());
    }

    @Test
    public void shouldGet() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);

        Event result = dao.get(1);

        assertEquals(event.getId(), result.getId());
    }

    @Test
    public void shouldRemove_success() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);

        boolean result = dao.remove(1);

        assertEquals(true, result);
    }

    @Test
    public void shouldDeleteEvent_throwException() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                dao.remove(3));

        assertEquals("Event doesn't exist with id: 3", thrown.getMessage());
    }

    @Test
    public void values(){
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

        List<Event> result = dao.values();

        assertEquals(1, result.size());
    }
}
