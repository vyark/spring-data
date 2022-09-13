package com.epam.dao;

import com.epam.model.Ticket;
import com.epam.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.storage.Storage.TICKET_PREFIX_ID;

@Repository
public class TicketDao {

    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void put(long id, Ticket ticket) {
        storage.getRepository().put(TICKET_PREFIX_ID + id, ticket);
    }

    public long size() {
        return storage.getRepository().keySet().stream().filter(s -> s.startsWith(TICKET_PREFIX_ID)).count();
    }

    public boolean remove(long ticketId) {
        if (storage.getRepository().remove(TICKET_PREFIX_ID + ticketId) != null) {
            return true;
        }
        throw new IllegalStateException("Ticket doesn't exist with id: " + ticketId);
    }

    public List<Ticket> values() {
        List<String> eventKeys =
                storage.getRepository().keySet().stream().filter(s -> s.startsWith(TICKET_PREFIX_ID)).collect(Collectors.toList());
        return eventKeys.stream().map(s -> (Ticket) storage.getRepository().get(s)).collect(Collectors.toList());
    }
}
