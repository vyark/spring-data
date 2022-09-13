package com.epam.model;

import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="tickets")
@Setter
public class TicketListContainer {
    private List<Ticket> ticketList;

    @XmlElement(name = "ticket")
    public List<Ticket> getTicketList() {
        return ticketList;
    }
}