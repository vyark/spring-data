package com.epam.dao;

import com.epam.model.Event;
import com.epam.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.storage.Storage.EVENT_PREFIX_ID;

@Repository
public class EventDao {
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    @Autowired
    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public Event put(long id, Event event) {
        storage.getRepository().put(EVENT_PREFIX_ID + id, event);
        return event;
    }

    public Event replace(long id, Event event) {
        storage.getRepository().replace(EVENT_PREFIX_ID + id, event);
        return event;
    }

    public Event get(long eventId) {
        return (Event) storage.getRepository().get(EVENT_PREFIX_ID + eventId);
    }

    public boolean remove(long eventId) {
        if (storage.getRepository().remove(EVENT_PREFIX_ID + eventId) != null) {
            return true;
        }
        throw new IllegalStateException("Event doesn't exist with id: " + eventId);
    }

    public List<Event> values() {
        List<String> eventKeys =
                storage.getRepository().keySet().stream().filter(s -> s.startsWith(EVENT_PREFIX_ID)).collect(Collectors.toList());
        return eventKeys.stream().map(s -> (Event)storage.getRepository().get(s)).collect(Collectors.toList());
    }
}
