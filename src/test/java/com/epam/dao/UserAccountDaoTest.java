package com.epam.dao;

import com.epam.model.UserAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserAccountDaoTest {

    @MockBean
    private UserAccountDao dao;

    private UserAccount userAccount;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        userAccount = new UserAccount();
        userAccount.setId(Long.valueOf(1));
        userAccount.setPrepaidAmount(new BigDecimal("50"));

    }

    @Test
    public void shouldPut() {
        when(dao.put(anyLong(), any())).thenReturn(userAccount);

        UserAccount result = dao.put(userAccount.getId(), userAccount);

        assertEquals(userAccount.getId(), result.getId());
    }

    @Test
    public void shouldGet() {
        when(dao.get(anyLong())).thenReturn(userAccount);

        UserAccount result = dao.get(userAccount.getId());

        assertEquals(userAccount.getId(), result.getId());
    }

    @Test
    public void shouldRemove() {
        when(dao.remove(anyLong())).thenReturn(true);

        boolean result = dao.remove(userAccount.getId());

        assertTrue(result);
    }
}
