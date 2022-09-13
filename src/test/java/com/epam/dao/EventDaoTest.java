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

public class EventDaoTest {
    @Mock
    private Storage storage;

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
        event.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        when(storage.getRepository()).thenReturn(new HashMap<>());

        Event result = dao.put(event.getId(), event);

        assertEquals(event.getId(), result.getId());
    }

    @Test
    public void shouldReplace() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");
        event.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        Event replaceEvent = new Event();
        replaceEvent.setId(2);
        replaceEvent.setTitle("Pop Music");
        replaceEvent.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        when(storage.getRepository()).thenReturn(new HashMap<>());

        dao.put(event.getId(), event);

        Event result = dao.replace(replaceEvent.getId(), replaceEvent);

        assertEquals(replaceEvent.getId(), result.getId());
    }

    @Test
    public void shouldGet() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");
        event.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);

        when(storage.getRepository()).thenReturn(repository);

        Event result = dao.get(1);

        assertEquals(event.getId(), result.getId());
    }

    @Test
    public void shouldRemove_success() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Rock Music");
        event.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);

        when(storage.getRepository()).thenReturn(repository);

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
        event.setDate(Date.from(Instant.parse("2023-10-10T20:00:00Z")));

        User user = new User();
        user.setId(1);
        user.setFirstName("Mary");
        user.setEmail("mary@mail.com");

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);
        repository.put(USER_PREFIX_ID + user.getId(), user);

        when(storage.getRepository()).thenReturn(repository);

        List<Event> result = dao.values();

        assertEquals(1, result.size());
    }
}
