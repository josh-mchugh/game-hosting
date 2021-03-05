package com.example.demo.framework.seed.user;

import com.example.demo.framework.seed.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.seed.user.projection.model.ExistsUserByEmailResponse;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserSeedServiceTest {

    @Autowired
    private UserSeedService userSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenUserDoesNotExistsThenReturnTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsUserByEmailQuery("admin@admin"), ExistsUserByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsUserByEmailResponse(false)));

        boolean doesNotExists = userSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenUserExistsThenReturnFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsUserByEmailQuery("admin@admin"), ExistsUserByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsUserByEmailResponse(true)));

        boolean doesNotExists = userSeedService.dataNotExists();

        Assertions.assertFalse(doesNotExists);
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
