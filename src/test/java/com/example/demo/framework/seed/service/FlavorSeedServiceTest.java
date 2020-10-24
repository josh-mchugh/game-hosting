package com.example.demo.framework.seed.service;

import com.example.demo.ovh.flavor.feign.FlavorClient;
import com.example.demo.ovh.flavor.feign.model.FlavorApi;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import feign.FeignException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FlavorSeedServiceTest {

    @Autowired
    private FlavorSeedService flavorSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    public FlavorClient flavorClient;

    @Test
    public void whenFlavorsDoNotExistsThenReturnTrue() {

        boolean doesNotExists = flavorSeedService.dataNotExists();

        Assertions.assertTrue(doesNotExists);
    }

    @Test
    public void whenFlavorExistsThenReturnFalse() {

        sampleBuilder.builder()
                .region()
                .flavor()
                .build();

        boolean doesNotExists = flavorSeedService.dataNotExists();

        Assertions.assertFalse(doesNotExists);
    }

    @Test
    public void whenInitializeDataIsValidThenReturnList() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        FlavorApi flavorApi = new FlavorApi();
        flavorApi.setFlavorId("flavor id");
        flavorApi.setRegionName(region.getName());

        Mockito.when(flavorClient.getFlavors(Mockito.anyString())).thenReturn(Collections.singletonList(flavorApi));

        ImmutableList<Object> flavors = flavorSeedService.initializeData();

        Assertions.assertEquals(1, flavors.size());
    }

    @Test
    public void whenApiReturnsEmptyListThenReturnEmptyList() {

        Mockito.when(flavorClient.getFlavors(Mockito.anyString())).thenReturn(Collections.emptyList());

        ImmutableList<Object> flavors = flavorSeedService.initializeData();

        Assertions.assertEquals(0, flavors.size());
    }

    @Test
    public void whenApiThrowsExceptionThenThrowException() {

        Mockito.when(flavorClient.getFlavors(Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> flavorSeedService.initializeData());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Flavor", flavorSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(flavorSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(3, flavorSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(flavorSeedService.order());
    }
}
