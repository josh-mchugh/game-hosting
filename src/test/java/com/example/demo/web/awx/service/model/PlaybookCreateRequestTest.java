package com.example.demo.web.awx.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlaybookCreateRequestTest {

    @Test
    public void whenRequestHasProjectIdThenReturnProjectId() {

        PlaybookCreateRequest request = new PlaybookCreateRequest(1L);

        Assertions.assertEquals(1L, request.getProjectId());
    }

    @Test
    public void whenRequestToString() {

        PlaybookCreateRequest request = request();

        String expected = "PlaybookCreateRequest(projectId=1)";

        Assertions.assertEquals(expected, request.toString());
    }

    @Test
    public void whenRequestHashCode() {

        PlaybookCreateRequest request = request();

        Assertions.assertEquals(60, request.hashCode());
    }

    @Test
    public void whenRequestEquals() {

        PlaybookCreateRequest request1 = request();
        PlaybookCreateRequest request2 = request();

        Assertions.assertEquals(request1, request2);
    }

    @Test
    public void whenRequestNotEquals() {

        PlaybookCreateRequest request = request();

        Assertions.assertNotEquals(request, new PlaybookCreateRequest(0L));
    }

    private PlaybookCreateRequest request() {

        return new PlaybookCreateRequest(1L);
    }
}
