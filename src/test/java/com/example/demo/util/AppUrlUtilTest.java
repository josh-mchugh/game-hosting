package com.example.demo.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

public class AppUrlUtilTest {

    @Test
    public void testGetAppUrl() {

        AppUrlUtil appUrlUtil =  new AppUrlUtil("http://localhost:8080");
        Assertions.assertEquals("http://localhost:8080", appUrlUtil.getAppUrl());
    }

    @Test
    public void testGetAppUrlPath() {

        AppUrlUtil appUrlUtil =  new AppUrlUtil("http://localhost:8080");
        Assertions.assertEquals("http://localhost:8080/login", appUrlUtil.getAppUrl("login"));
    }

    @Test
    public void testGetAppUrlPathWithParam() {

        String encodedUrl = UriComponentsBuilder.fromHttpUrl("http://localhost:8080")
                .path("/login")
                .queryParam("value", "Some Special Name -/%#$%&!")
                .toUriString();

        AppUrlUtil appUrlUtil = new AppUrlUtil("http://localhost:8080");
        Assertions.assertEquals(encodedUrl, appUrlUtil.getAppUrl("login", ImmutablePair.of("value", "Some Special Name -/%#$%&!")));
    }
}
