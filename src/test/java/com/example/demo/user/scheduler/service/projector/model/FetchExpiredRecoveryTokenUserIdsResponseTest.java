package com.example.demo.user.scheduler.service.projector.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.UUID;

public class FetchExpiredRecoveryTokenUserIdsResponseTest {

    @Test
    public void whenModelHasPageThenReturnPage() {

        Page<UUID> page = new PageImpl<>(Collections.singletonList(UUID.randomUUID()));
        FetchExpiredRecoveryTokenUserIdsResponse model = new FetchExpiredRecoveryTokenUserIdsResponse(page);

        Assertions.assertEquals(page, model.getRecoveryTokens());
    }

    @Test
    public void whenModelToString() {

        FetchExpiredRecoveryTokenUserIdsResponse model = model();

        String expected = "FetchExpiredRecoveryTokenUserIdsResponse(recoveryTokens=Page 1 of 1 containing java.util.UUID instances)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchExpiredRecoveryTokenUserIdsResponse model = new FetchExpiredRecoveryTokenUserIdsResponse(null);

        Assertions.assertEquals(102, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchExpiredRecoveryTokenUserIdsResponse model1 = model();
        FetchExpiredRecoveryTokenUserIdsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchExpiredRecoveryTokenUserIdsResponse model = model();

        Assertions.assertNotEquals(model, new FetchExpiredRecoveryTokenUserIdsResponse(null));
    }

    private FetchExpiredRecoveryTokenUserIdsResponse model() {

        Page<UUID> page = new PageImpl<>(Collections.singletonList(UUID.fromString("a11241bf-caf6-4cca-9f42-234ea5c90837")));
        return new FetchExpiredRecoveryTokenUserIdsResponse(page);
    }
}
