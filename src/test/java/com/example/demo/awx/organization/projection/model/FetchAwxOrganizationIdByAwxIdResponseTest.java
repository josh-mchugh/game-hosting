package com.example.demo.awx.organization.projection.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FetchAwxOrganizationIdByAwxIdResponseTest {

    @Test
    public void whenResponseHasIdThenReturnId() {

        FetchAwxOrganizationIdByAwxIdResponse response = new FetchAwxOrganizationIdByAwxIdResponse("id");

        Assertions.assertEquals("id", response.getId());
    }

    @Test
    public void whenResponseToString() {

        FetchAwxOrganizationIdByAwxIdResponse response = response();

        String expected = "FetchAwxOrganizationIdByAwxIdResponse(id=id)";

        Assertions.assertEquals(expected, response.toString());
    }

    @Test
    public void whenResponseHashCode() {

        FetchAwxOrganizationIdByAwxIdResponse response = response();

        Assertions.assertEquals(3414, response.hashCode());
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

        Assertions.assertNotEquals(response, new FetchAwxOrganizationIdByAwxIdResponse(""));
    }

    private FetchAwxOrganizationIdByAwxIdResponse response() {

        return new FetchAwxOrganizationIdByAwxIdResponse("id");
    }
}
