package com.example.demo.framework.database;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class JPQLQueryFactoryConfig {

    private final EntityManager entityManager;

    @Bean
    public JPQLQueryFactory jpqlQueryFactory() {

        return new JPAQueryFactory(entityManager);
    }
}
