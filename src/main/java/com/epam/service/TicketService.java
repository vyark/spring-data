package com.epam.service;

import com.epam.dao.TicketDao;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {

    @Autowired
    private TicketDao ticketDao;
    @Transactional
    public Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDao.size() + 1);
        ticket.setEvent(new Event(eventId));
        ticket.setUser(new User(userId));
        ticket.setCategory(category);
        ticket.setPlace(place);
        ticketDao.put(ticket.getId(), ticket);
        return ticket;
    }
    @Transactional
    public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;

        return ticketDao.values().stream()
                        .filter(ticket -> ticket.getUser().getId() == user.getId())
                        .skip(skipCount)
                        .limit(pageSize)
                        .collect(Collectors.toList());
    }
    @Transactional
    public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;

        return ticketDao.values().stream()
                        .filter(ticket -> ticket.getEvent().getId() == event.getId())
                        .skip(skipCount)
                        .limit(pageSize)
                        .collect(Collectors.toList());
    }
    @Transactional
    public boolean cancelTicket(long ticketId) {
        return ticketDao.remove(ticketId);
    }

    @Transactional
    public Ticket getTicketById(long ticketId) {
        return ticketDao.values().stream()
                        .filter(ticket -> ticket.getId() == ticketId).findFirst().get();
    }
}
