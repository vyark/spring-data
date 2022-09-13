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
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetUser() throws Exception {
        this.mockMvc.perform(get("/user")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Users")));
    }

    @Test
    public void shouldGetUserById() throws Exception {
        this.mockMvc.perform(get("/user/id/1")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("John")));
    }

    @Test
    public void shouldGetUserByEmail() throws Exception {
        this.mockMvc.perform(get("/user/email/john@mail.com")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("John")));
    }
}
