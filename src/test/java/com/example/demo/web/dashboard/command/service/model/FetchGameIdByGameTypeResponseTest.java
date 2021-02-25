package com.example.demo.web.dashboard.command.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class FetchGameIdByGameTypeResponseTest {

    @Test
    public void whenModelHasIdThenReturnId() {

        UUID id = UUID.randomUUID();

        FetchGameIdByGameTypeResponse model = new FetchGameIdByGameTypeResponse(id);

        Assertions.assertEquals(id, model.getId());
    }

    @Test
    public void whenModelToString() {

        FetchGameIdByGameTypeResponse model = model();

        String expected = "FetchGameIdByGameTypeResponse(id=a28375c4-2b8d-44cb-82ab-d51005ba1b47)";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        FetchGameIdByGameTypeResponse model = model();

        Assertions.assertEquals(236978067, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        FetchGameIdByGameTypeResponse model1 = model();
        FetchGameIdByGameTypeResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        FetchGameIdByGameTypeResponse model = model();

        Assertions.assertNotEquals(model, new FetchGameIdByGameTypeResponse(null));
    }

    private FetchGameIdByGameTypeResponse model() {

        return new FetchGameIdByGameTypeResponse(UUID.fromString("a28375c4-2b8d-44cb-82ab-d51005ba1b47"));
    }
}
