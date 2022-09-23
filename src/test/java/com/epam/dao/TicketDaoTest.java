package com.epam.dao;

import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.storage.Storage.EVENT_PREFIX_ID;
import static com.epam.storage.Storage.TICKET_PREFIX_ID;
import static com.epam.storage.Storage.USER_PREFIX_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TicketDaoTest {
    @InjectMocks
    private TicketDao dao;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPut() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setEvent(new Event(1));
        ticket.setUser(new User(1));
        ticket.setCategory(Ticket.Category.STANDARD);
        ticket.setPlace(1);

        dao.put(ticket.getId(), ticket);
    }

    @Test
    public void shouldRemove_success() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setEvent(new Event(1));
        ticket.setUser(new User(1));
        ticket.setCategory(Ticket.Category.STANDARD);
        ticket.setPlace(1);

        Map<String, Object> repository = new HashMap<>();
        repository.put(TICKET_PREFIX_ID + ticket.getId(), ticket);

        boolean result = dao.remove(1);

        assertEquals(true, result);
    }

    @Test
    public void shouldDeleteTicket_throwException() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                dao.remove(3));

        assertEquals("Ticket doesn't exist with id: 3", thrown.getMessage());
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

        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setCategory(Ticket.Category.STANDARD);
        ticket.setPlace(1);

        Map<String, Object> repository = new HashMap<>();
        repository.put(EVENT_PREFIX_ID + event.getId(), event);
        repository.put(USER_PREFIX_ID + user.getId(), user);
        repository.put(TICKET_PREFIX_ID + ticket.getId(), ticket);

        List<Ticket> result = dao.values();

        assertEquals(1, result.size());
    }
}
