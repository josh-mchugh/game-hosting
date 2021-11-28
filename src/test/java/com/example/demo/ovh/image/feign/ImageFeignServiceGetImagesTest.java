package com.example.demo.ovh.image.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.image.feign.model.ImageApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ImageFeignServiceGetImagesTest {

    private OvhConfig ovhConfig;
    private ImageClient imageClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        imageClient = Mockito.mock(ImageClient.class);
    }

    @Test
    public void whenGetImagesThenExpectResults() {

        List<ImageApi> expected = Arrays.asList(new ImageApi(), new ImageApi());

        Mockito.when(imageClient.getImages(Mockito.anyString())).thenReturn(expected);

        ImageFeignServiceImpl service = new ImageFeignServiceImpl(ovhConfig, imageClient);

        Assertions.assertEquals(expected, service.getImages());
    }
}
