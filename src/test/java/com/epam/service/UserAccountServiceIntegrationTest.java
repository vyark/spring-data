package com.epam.service;

import com.epam.model.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserAccountServiceIntegrationTest {

    @Autowired
    private UserAccountService service;

    @Test
    public void shouldGetUserAccountById() {
        UserAccount result = service.getUserAccountById(1);

        assertEquals(1, result.getId());
    }
}
