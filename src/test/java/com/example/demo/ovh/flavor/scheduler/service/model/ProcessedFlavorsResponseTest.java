package com.example.demo.ovh.flavor.scheduler.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProcessedFlavorsResponseTest {

    @Test
    public void whenModelHasUpdatedFlavorThenReturnUpdatedFlavor() {

        Object expected = new Object();

        ProcessedFlavorsResponse model = ProcessedFlavorsResponse.builder()
                .updatedFlavor(expected)
                .build();

        Assertions.assertEquals(Collections.singletonList(expected), model.getUpdatedFlavors());
    }

    @Test
    public void whenModelHasUpdatesFlavorsThenReturnUpdatedFlavors() {

        List<Object> expected = Arrays.asList(new Object(), new Object());

        ProcessedFlavorsResponse model = ProcessedFlavorsResponse.builder()
                .updatedFlavors(expected)
                .build();

        Assertions.assertEquals(expected, model.getUpdatedFlavors());
    }

    @Test
    public void whenModelHasClearedUpdateFlavorsThenExpectEmptyList() {

        ProcessedFlavorsResponse model = ProcessedFlavorsResponse.builder()
                .updatedFlavors(Arrays.asList(new Object(), new Object()))
                .clearUpdatedFlavors()
                .build();

        Assertions.assertEquals(new ArrayList<>(), model.getUpdatedFlavors());
    }

    @Test
    public void whenModelHasCreatedFlavorThenReturnFlavor() {

        Object expected = new Object();

        ProcessedFlavorsResponse model = ProcessedFlavorsResponse.builder()
                .createdFlavor(expected)
                .build();

        Assertions.assertEquals(Collections.singletonList(expected), model.getCreatedFlavors());
    }

    @Test
    public void whenModelHasCreatedFlavorsThenReturnFlavors() {

        List<Object> expected = Arrays.asList(new Object(), new Object());

        ProcessedFlavorsResponse model = ProcessedFlavorsResponse.builder()
                .createdFlavors(expected)
                .build();

        Assertions.assertEquals(expected, model.getCreatedFlavors());
    }

    @Test
    public void whenModelHasClearedCreatedFlavorsThenReturnEmptyList() {

        ProcessedFlavorsResponse model = ProcessedFlavorsResponse.builder()
                .createdFlavors(Arrays.asList(new Object(), new Object()))
                .clearCreatedFlavors()
                .build();

        Assertions.assertEquals(new ArrayList<>(), model.getCreatedFlavors());
    }

    @Test
    public void whenModelToString() {

        ProcessedFlavorsResponse model = model();

        String expected = "ProcessedFlavorsResponse(updatedFlavors=[updated1, updated2, updated3], createdFlavors=[created1, created2, created3])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProcessedFlavorsResponse model = model();

        Assertions.assertEquals(925275604, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

        ProcessedFlavorsResponse model1 = model();
        ProcessedFlavorsResponse model2 = model();

        Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProcessedFlavorsResponse model = model();

        Assertions.assertNotEquals(model, ProcessedFlavorsResponse.builder().build());
    }

    private ProcessedFlavorsResponse model() {

        return ProcessedFlavorsResponse.builder()
                .createdFlavors(Arrays.asList("created1", "created2"))
                .createdFlavor("created3")
                .updatedFlavors(Arrays.asList("updated1", "updated2"))
                .updatedFlavor("updated3")
                .build();
    }
}
