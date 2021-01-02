package com.example.demo.framework.database;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;

public class JPQLQueryFactoryConfigTest {

    @Test
    public void whenConfigJPQLQueryFactoryThenReturnJPQLQueryFactory() {

        EntityManager entityManager = Mockito.mock(EntityManager.class);

        JPQLQueryFactoryConfig config = new JPQLQueryFactoryConfig(entityManager);

        Assertions.assertNotNull(config.jpqlQueryFactory());
    }
}
