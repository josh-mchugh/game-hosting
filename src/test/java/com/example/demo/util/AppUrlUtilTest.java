package com.example.demo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
