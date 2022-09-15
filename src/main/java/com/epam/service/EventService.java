package com.epam.service;

import com.epam.dao.EventDao;
import com.epam.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventDao eventDao;

    @Transactional
    public List<Event> getEvents() {
        return eventDao.values();
    }

    @Transactional
    public Event getEventById(long eventId) {
        return eventDao.get(eventId);
    }

    @Transactional
    public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;

        return eventDao.values().stream()
                .filter(event -> event.getTitle().equals(title))
                .skip(skipCount)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
        int skipCount = (pageNum - 1) * pageSize;

        return eventDao.values().stream()
                .skip(skipCount)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Transactional
    public Event createEvent(Event event) {
        return eventDao.put(event.getId(), event);
    }

    @Transactional
    public Event updateEvent(Event event) {
        return eventDao.replace(event.getId(), event);
    }

    @Transactional
    public boolean deleteEvent(long eventId) {
        return eventDao.remove(eventId);
    }
}
