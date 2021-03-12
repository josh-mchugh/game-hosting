package com.example.demo.framework.seed.ovh.image;

import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;
import com.example.demo.ovh.image.feign.IImageFeignService;
import com.example.demo.ovh.image.feign.model.ImageApi;
import com.example.demo.ovh.region.entity.model.Region;
import com.example.demo.sample.SampleBuilder;
import com.google.common.collect.ImmutableList;
import feign.FeignException;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageSeedServiceTest {

    @Autowired
    private ImageSeedService imageSeedService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @MockBean
    private IImageFeignService imageFeignService;

    @MockBean
    private QueryGateway queryGateway;

    @Test
    public void whenImageExistsThenDataNotExistsReturnFalse() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyImageQuery(), ExistsAnyImageResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyImageResponse(true)));

        Assertions.assertFalse(imageSeedService.dataNotExists());
    }

    @Test
    public void whenImageDoesNotExistsThenDataNotExistsReturnTrue() throws ExecutionException, InterruptedException {

        Mockito.when(queryGateway.query(new ExistsAnyImageQuery(), ExistsAnyImageResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new ExistsAnyImageResponse(false)));

        Assertions.assertTrue(imageSeedService.dataNotExists());
    }

    @Test
    public void whenApiReturnsEmptyListThenReturnEmptyList() {

        Mockito.when(imageFeignService.getImages()).thenReturn(Collections.emptyList());

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
        imageApi.setId("ovhId");
        imageApi.setName("Ubuntu 20.4");
        imageApi.setRegionName(region.getName());

        Mockito.when(imageFeignService.getImages()).thenReturn(Collections.singletonList(imageApi));

        ImmutableList<Object> images = imageSeedService.initializeData();

        Assertions.assertEquals(1, images.size());
    }

    @Test
    public void whenApiThrowsExceptionThenThrowThrownException() {

        Mockito.when(imageFeignService.getImages()).thenThrow(FeignException.FeignClientException.class);

        Assertions.assertThrows(FeignException.FeignClientException.class, () -> imageSeedService.initializeData());
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