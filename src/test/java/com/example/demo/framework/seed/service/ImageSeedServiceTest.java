package com.example.demo.framework.seed.service;

import com.example.demo.ovh.image.feign.ImageClient;
import com.example.demo.ovh.image.feign.model.ImageApi;
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
public class ImageSeedServiceTest {

    @Autowired
    private ImageSeedService imageSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private ImageClient imageClient;

    @Test
    public void whenImageExistsThenDataNotExistsReturnFalse() {

        sampleBuilder.builder()
                .region()
                .image()
                .build();

        Assertions.assertFalse(imageSeedService.dataNotExists());
    }

    @Test
    public void whenApiReturnsEmptyListThenReturnEmptyList() {

        Mockito.when(imageClient.getImages(Mockito.anyString())).thenReturn(Collections.emptyList());

        ImmutableList<Object> images = imageSeedService.initializeData();

        Assertions.assertEquals(0, images.size());
    }

    @Test
    public void whenApiIsValidThenReturnList() {

        Region region = sampleBuilder.builder()
                .region()
                .build()
                .getRegion();

        ImageApi imageApi = new ImageApi();
        imageApi.setImageId("image id");
        imageApi.setName("Ubuntu 20.4");
        imageApi.setRegionName(region.getName());

        Mockito.when(imageClient.getImages(Mockito.anyString())).thenReturn(Collections.singletonList(imageApi));

        ImmutableList<Object> images = imageSeedService.initializeData();

        Assertions.assertEquals(1, images.size());
    }

    @Test
    public void whenApiThrowsExceptionThenThrowThrownException() {

        Mockito.when(imageClient.getImages(Mockito.anyString())).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> imageSeedService.initializeData());
    }

    @Test
    public void whenImageDoesNotExistThenDataNotExistsReturnsTrue() {

        Assertions.assertTrue(imageSeedService.dataNotExists());
    }

    @Test
    public void whenTypeHasValueThenReturnValue() {

        Assertions.assertEquals("Image", imageSeedService.type());
    }

    @Test
    public void typeShouldNotBeNull() {

        Assertions.assertNotNull(imageSeedService.type());
    }

    @Test
    public void whenOrderHasValueThenReturnValue() {

        Assertions.assertEquals(4, imageSeedService.order());
    }

    @Test
    public void orderShouldNotBeNull() {

        Assertions.assertNotNull(imageSeedService.order());
    }
}
