package com.epam.controllers;

import com.epam.model.Ticket;
import com.epam.model.TicketListContainer;
import com.epam.model.User;
import com.epam.service.EventService;
import com.epam.service.TicketService;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;

    @GetMapping("/ticket")
    public ModelAndView getTicket() {
        ModelAndView modelAndView = new ModelAndView("ticket");
        modelAndView.addObject("tickets",
                ticketService.getBookedTickets(userService.getUserById(Long.parseLong("1")), 5, 1));
        return modelAndView;
    }

    @GetMapping("/ticket/user/{id}")
    public ModelAndView getTicketsByUser(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("ticket");
        modelAndView.addObject("tickets",
                ticketService.getBookedTickets(userService.getUserById(Long.parseLong(id)), 5, 1));
        return modelAndView;
    }

    @GetMapping("/ticket/event/{id}")
    public ModelAndView getTicketsByEvent(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("ticket");
        modelAndView.addObject("tickets",
                ticketService.getBookedTickets(eventService.getEventById(Long.parseLong(id)), 5,
                        1));
        return modelAndView;
    }

    @GetMapping("/ticket/{id}")
    public ModelAndView cancelTicket(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("ticket");
        ticketService.cancelTicket(Long.parseLong(id));
        modelAndView.addObject("tickets",
                ticketService.getBookedTickets(userService.getUserById(Long.parseLong(id)), 5, 1));
        return modelAndView;
    }

    @GetMapping("/ticket/pdf/{id}")
    public ModelAndView printTicket(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("pdf");
        User user = userService.getUserById(Long.parseLong(id));
        List<Ticket> tickets = ticketService.getBookedTickets(user, 10, 1);
        modelAndView.addObject("tickets", tickets);
        return modelAndView;
    }
}
