package com.epam.storage;

import com.epam.mapper.ObjXMLMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class StorageTest {

    @Mock
    private ObjXMLMapper objXMLMapper;
    @InjectMocks
    private Storage storage;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        storage.initialize();

        assertEquals(20, storage.getRepository().size());
    }
}
