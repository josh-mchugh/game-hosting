package com.example.demo.ovh.image.scheduler.service.model;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class ProcessedImageResponseTest {

    @Test
    public void whenResponseHasCreatedImageThenReturnCreatedImage() {

        ProcessedImagesResponse response = ProcessedImagesResponse.builder()
                .createdImage("id")
                .build();

        Assertions.assertEquals("id", response.getCreatedImages().get(0));
    }

    @Test
    public void whenResponseHasCreatedImagesThenReturnCreatedImages() {

        ProcessedImagesResponse response = ProcessedImagesResponse.builder()
                .createdImages(Collections.singletonList("id"))
                .build();

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getCreatedImages()));
    }

    @Test
    public void whenResponseDoesNotHaveCreatedImagesThenReturnEmpty() {

        ProcessedImagesResponse response = ProcessedImagesResponse.builder().build();

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getCreatedImages()));
    }

    @Test
    public void whenResponseHasUpdatedImageThenReturnUpdatedImage() {

        ProcessedImagesResponse response = ProcessedImagesResponse.builder()
                .updatedImage("id")
                .build();

        Assertions.assertEquals("id", response.getUpdatedImages().get(0));
    }

    @Test
    public void whenResponseHasUpdatedImagesThenReturnUpdatesImages() {

        ProcessedImagesResponse response = ProcessedImagesResponse.builder()
                .updatedImages(Collections.singletonList("id"))
                .build();

        Assertions.assertTrue(CollectionUtils.isNotEmpty(response.getUpdatedImages()));
    }

    @Test
    public void whenResponseDoesNotHaveUpdatedImageThenReturnEmpty() {

        ProcessedImagesResponse response = ProcessedImagesResponse.builder().build();

        Assertions.assertTrue(CollectionUtils.isEmpty(response.getUpdatedImages()));
    }

    @Test
    public void whenResponseToString() {

        ProcessedImagesResponse response = response();

        String toString = "ProcessedImagesResponse(createdImages=[id], updatedImages=[id])";

        Assertions.assertEquals(toString, response.toString());
    }

    @Test
    public void whenResponseToHashCode() {

        ProcessedImagesResponse response = response();

        Assertions.assertEquals(206641, response.hashCode());
    }

    @Test
    public void whenResponseEquals() {

        ProcessedImagesResponse response1 = response();
        ProcessedImagesResponse response2 = response();

        Assertions.assertEquals(response1, response2);
    }

    @Test
    public void whenResponseNotEquals() {

        ProcessedImagesResponse response = response();

        Assertions.assertNotEquals(response, ProcessedImagesResponse.builder().build());
    }

    private ProcessedImagesResponse response() {

        return ProcessedImagesResponse.builder()
                .createdImage("id")
                .updatedImage("id")
                .build();
    }
}
