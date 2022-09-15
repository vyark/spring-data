package com.epam.service;

import com.epam.dao.EventDao;
import com.epam.dao.TicketDao;
import com.epam.dao.UserAccountDao;
import com.epam.model.Event;
import com.epam.model.Ticket;
import com.epam.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private TicketDao ticketDao;

    @Transactional
    public UserAccount getUserAccountById(long userAccountId) {
        return userAccountDao.get(userAccountId);
    }

    @Transactional
    public BigDecimal checkAndWithdrawPrepaidAmount(long ticketId, BigDecimal ticketPrice) {
        Ticket ticket = ticketDao.get(ticketId);

        Event event = eventDao.get(ticket.getEvent().getId());

        return userAccountDao.get(event.getId()).getPrepaidAmount().subtract(ticketPrice);
    }

}
