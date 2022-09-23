package com.epam.dao;

import com.epam.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDao extends CrudRepository<Ticket, Integer> {

    void put(long id, Ticket ticket);

    boolean remove(long ticketId);

    Ticket get(long ticketId);

    List<Ticket> values();
}
