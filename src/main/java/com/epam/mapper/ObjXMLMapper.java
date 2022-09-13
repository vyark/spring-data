package com.epam.mapper;

import com.epam.model.Ticket;
import com.epam.model.TicketListContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Component
public class ObjXMLMapper {

    private static final String FILE_NAME = "tickets.xml";
    @Autowired
    private Jaxb2Marshaller unmarshaller;

    public List<Ticket> XMLToObj() throws IOException {
        TicketListContainer ticketList;
        try (FileInputStream is = new FileInputStream(FILE_NAME)) {
            ticketList = (TicketListContainer) this.unmarshaller.unmarshal(new StreamSource(is));
        }
        return ticketList.getTicketList();
    }
}