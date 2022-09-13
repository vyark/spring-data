package com.epam.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TicketControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetTicket() throws Exception {
        this.mockMvc.perform(get("/ticket")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Tickets")));
    }

    @Test
    public void shouldGetTicketByUserId() throws Exception {
        this.mockMvc.perform(get("/ticket/user/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("STANDARD")));
    }

    @Test
    public void shouldGetTicketByEventId() throws Exception {
        this.mockMvc.perform(get("/ticket/event/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("STANDARD")));
    }

    @Test
    public void shouldCancelTicketById() throws Exception {
        this.mockMvc.perform(get("/ticket/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldGetPdf() throws Exception {
        this.mockMvc.perform(get("/ticket/pdf/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("STANDARD")));
    }
}
