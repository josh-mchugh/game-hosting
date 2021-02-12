package com.example.demo.awx.organization.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchAwxOrganizationIdByAwxIdResponseTest {

    @Test
    public void whenResponseHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchAwxOrganizationIdByAwxIdResponse response = new FetchAwxOrganizationIdByAwxIdResponse(id.toString());

        Assertions.assertEquals(id, response.getId());
    }

    @Test
    public void whenResponseToString() {

        FetchAwxOrganizationIdByAwxIdResponse response = response();

        String expected = "FetchAwxOrganizationIdByAwxIdResponse(id=c33098dd-5f29-4c79-93fc-574dd8b3fccc)";

        Assertions.assertEquals(expected, response.toString());
    }

    @Test
    public void whenResponseHashCode() {

        FetchAwxOrganizationIdByAwxIdResponse response = response();

        Assertions.assertEquals(-682197152, response.hashCode());
    }

    @Test
    public void whenResponseEquals() {

        FetchAwxOrganizationIdByAwxIdResponse response1 = response();
        FetchAwxOrganizationIdByAwxIdResponse response2 = response();

        Assertions.assertEquals(response1, response2);
    }

    @Test
    public void whenResponseNotEquals() {

        FetchAwxOrganizationIdByAwxIdResponse response = response();

        Assertions.assertNotEquals(response, new FetchAwxOrganizationIdByAwxIdResponse(UUID.randomUUID().toString()));
    }

    private FetchAwxOrganizationIdByAwxIdResponse response() {

        return new FetchAwxOrganizationIdByAwxIdResponse("c33098dd-5f29-4c79-93fc-574dd8b3fccc");
    }
}
