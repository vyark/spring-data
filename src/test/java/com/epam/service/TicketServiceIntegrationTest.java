package com.epam.service;

import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TicketServiceIntegrationTest {

    @Autowired
    private TicketService service;

    public void shouldBookTicket() {
        Ticket result = service.bookTicket(1, 1, 3, Ticket.Category.STANDARD);

        assertEquals(3, result.getId());
    }

    @Test
    public void shouldGetBookedTicketsByUser() {
        User user = new User();
        user.setId(1);
        user.setFirstName("John");
        user.setEmail("john@mail.com");

        List<Ticket> result = service.getBookedTickets(user, 5, 1);

        assertEquals(2, result.size());
    }

    @Test
    public void shouldGetBookedTicketsByEvent() {
        Event event = new Event();
        event.setId(1);
        event.setTitle("Classical Music");
        event.setDate(Date.from(Instant.parse("2022-12-04T20:00:00Z")));

        List<Ticket> result = service.getBookedTickets(event, 5, 1);

        assertEquals(1, result.size());
    }

    @Test
    public void shouldCancelTicket_success() {
        boolean result = service.cancelTicket(2);

        assertEquals(true, result);
    }

    @Test
    public void shouldCancelTicket_throwException() {
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () ->
                service.cancelTicket(3));

        assertEquals("Ticket doesn't exist with id: 3", thrown.getMessage());
    }
}
