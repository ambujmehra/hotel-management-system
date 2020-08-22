package com.marriott.hms.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryIntegrationTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testALlInjectsNotNull() {
        Assert.assertNotNull(roomRepository);
    }


}
