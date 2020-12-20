package com.example.demo.ovh.region.scheduler.service.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ProcessRegionResponseTest {

    @Test
    public void whenModelHasUpdatedRegionThenReturnUpdatedRegion() {

        ProcessRegionResponse model = ProcessRegionResponse.builder()
                .updatedRegion("region")
                .build();

        Assertions.assertEquals(Collections.singletonList("region"), model.getUpdatedRegions());
    }

    @Test
    public void whenModelHasUpdatedRegionsThenReturnUpdatedRegions() {

        ProcessRegionResponse model = ProcessRegionResponse.builder()
                .updatedRegions(Arrays.asList("region1", "region2"))
                .build();

        Assertions.assertEquals(Arrays.asList("region1", "region2"), model.getUpdatedRegions());
    }

    @Test
    public void whenModelHasUpdatedRegionsClearedThenReturnEmptyList() {

        ProcessRegionResponse model = ProcessRegionResponse.builder()
                .updatedRegions(Arrays.asList("region1", "region2"))
                .clearUpdatedRegions()
                .build();

        Assertions.assertEquals(new ArrayList<>(), model.getUpdatedRegions());
    }

    @Test
    public void whenModelHasCreatedRegionThenReturnCreatedRegion() {

        ProcessRegionResponse model = ProcessRegionResponse.builder()
                .createdRegion("region")
                .build();

        Assertions.assertEquals(Collections.singletonList("region"), model.getCreatedRegions());
    }

    @Test
    public void whenModelHasCreatedRegionsThenReturnCreatedRegions() {

        ProcessRegionResponse model = ProcessRegionResponse.builder()
                .createdRegions(Arrays.asList("region1", "region2"))
                .build();

        Assertions.assertEquals(Arrays.asList("region1", "region2"), model.getCreatedRegions());
    }

    @Test
    public void whenModelHasClearedCreatedRegionsThenExpectEmptyList() {

        ProcessRegionResponse model = ProcessRegionResponse.builder()
                .createdRegions(Arrays.asList("region1", "region2"))
                .clearCreatedRegions()
                .build();

        Assertions.assertEquals(new ArrayList<>(), model.getCreatedRegions());
    }

    @Test
    public void whenModelToString() {

        ProcessRegionResponse model = model();

        String expected = "ProcessRegionResponse(updatedRegions=[region1, region2, region3], createdRegions=[region1, region2, region3])";

        Assertions.assertEquals(expected, model.toString());
    }

    @Test
    public void whenModelHashCode() {

        ProcessRegionResponse model = model();

        Assertions.assertEquals(-1858760091, model.hashCode());
    }

    @Test
    public void whenModelEquals() {

       ProcessRegionResponse model1 = model();
       ProcessRegionResponse model2 = model();

       Assertions.assertEquals(model1, model2);
    }

    @Test
    public void whenModelNotEquals() {

        ProcessRegionResponse model = model();

        Assertions.assertNotEquals(model, ProcessRegionResponse.builder().build());
    }

    private ProcessRegionResponse model() {

        return ProcessRegionResponse.builder()
                .createdRegions(Arrays.asList("region1", "region2"))
                .createdRegion("region3")
                .updatedRegions(Arrays.asList("region1", "region2"))
                .updatedRegion("region3")
                .build();
    }
}
