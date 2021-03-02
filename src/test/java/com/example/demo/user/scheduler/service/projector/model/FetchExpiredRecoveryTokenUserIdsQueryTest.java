package com.example.demo.user.scheduler.service.projector.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class FetchExpiredRecoveryTokenUserIdsQueryTest {

    @Test
    public void whenModelHasPageableThenReturnPageable() {

        Pageable pageable = PageRequest.of(0, 10);
        FetchExpiredRecoveryTokenUserIdsQuery model = new FetchExpiredRecoveryTokenUserIdsQuery(pageable);

        Assertions.assertEquals(pageable, model.getPageable());
    }

    @Test
    public void whenModelToString() {

        FetchExpiredRecoveryTokenUserIdsQuery model = model();

        String expected = "FetchExpiredRecoveryTokenUserIdsQuery(pageable=Page request [number: 0, size 10, sort: UNSORTED])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchExpiredRecoveryTokenUserIdsQuery model = model();

        Assertions.assertEquals(30688, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchExpiredRecoveryTokenUserIdsQuery model1 = model();
        FetchExpiredRecoveryTokenUserIdsQuery model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchExpiredRecoveryTokenUserIdsQuery model = model();

        Assertions.assertNotEquals(model, new FetchExpiredRecoveryTokenUserIdsQuery(null));
    }

    private FetchExpiredRecoveryTokenUserIdsQuery model() {

        return new FetchExpiredRecoveryTokenUserIdsQuery(PageRequest.of(0, 10));
    }
}
