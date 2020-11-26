package com.example.demo.framework.seed.service;

import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserSeedServiceTest {

    @Autowired
    private UserSeedService userSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenUsersDoNotExistThenReturnTrue() {

        boolean doesNotExists = userSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenUserDoExistThenReturnFalse() {

        sampleBuilder.builder()
                .user()
                .build();

        boolean doesNotExists = userSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenInitializeDataIsValueReturnList() {

        ImmutableList<Object> users = userSeedService.initializeData();

        Assertions.assertEquals(1, users.size());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("User", userSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(userSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(1, userSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(userSeedService.order());
    }
}
