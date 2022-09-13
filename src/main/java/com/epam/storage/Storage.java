package com.epam.storage;

import com.epam.mapper.ObjXMLMapper;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Storage {

    public static final String TICKET_PREFIX_ID = "ticket:";
    public static final String EVENT_PREFIX_ID = "event:";
    public static final String USER_PREFIX_ID = "user:";

    private Map<String, Object> repository = new HashMap<>();
    @Autowired
    private ObjXMLMapper objXMLMapper;

    @SneakyThrows
    @PostConstruct
    public void initialize() {
        ObjectMapper objectMapperEvents = new ObjectMapper();
        InputStream inputStreamEvents = Event.class.getResourceAsStream("/data/events.json");

        TypeReference<List<Event>> typeReferenceEvents = new TypeReference<List<Event>>() {
        };
        List<Event> resultEvents = objectMapperEvents.readValue(inputStreamEvents,
                typeReferenceEvents);
        for (Event event : resultEvents) {
            repository.put(EVENT_PREFIX_ID + event.getId(), event);
        }

        ObjectMapper objectMapperUsers = new ObjectMapper();
        InputStream inputStreamUsers = User.class.getResourceAsStream("/data/users.json");

        TypeReference<List<User>> typeReferenceUsers = new TypeReference<List<User>>() {
        };
        List<User> resultUsers = objectMapperUsers.readValue(inputStreamUsers, typeReferenceUsers);
        for (User user : resultUsers) {
            repository.put(USER_PREFIX_ID + user.getId(), user);
        }

        List<Ticket> ticketList = objXMLMapper.XMLToObj();
        for (Ticket ticket : ticketList) {
            repository.put(TICKET_PREFIX_ID + ticket.getId(), ticket);
        }
    }

    public Map<String, Object> getRepository() {
        return repository;
    }
}
