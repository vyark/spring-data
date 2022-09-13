package com.epam.controllers;

import com.epam.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event")
    public ModelAndView getEvents() {
        ModelAndView modelAndView = new ModelAndView("event");
        modelAndView.addObject("events", eventService.getEvents());
        return modelAndView;
    }

    @GetMapping("/event/id/{id}")
    public ModelAndView getEventById(@PathVariable(name = "id") String id) {
        ModelAndView modelAndView = new ModelAndView("event");
        System.out.println(eventService.getEventById(Long.parseLong(id)));
        modelAndView.addObject("events",
                Arrays.asList(eventService.getEventById(Long.parseLong(id))));
        return modelAndView;
    }

    @GetMapping("/event/title/{title}")
    public ModelAndView getEventsByTitle(@PathVariable(name = "title") String title) {
        ModelAndView modelAndView = new ModelAndView("event");
        modelAndView.addObject("events", eventService.getEventsByTitle(title, 5, 1));
        return modelAndView;
    }
}
