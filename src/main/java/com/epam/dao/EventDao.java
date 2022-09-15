package com.epam.dao;

import com.epam.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface EventDao extends CrudRepository<Event, Integer> {

    //The implementation class of the below methods will be created by Spring at runtime automatically.
    Event put(long id, Event event);

    Event replace(long id, Event event);

    Event get(long eventId);

    boolean remove(long eventId);

    List<Event> values();

    Event getByTicketPrice(long eventId, BigDecimal ticketPrice);
}
