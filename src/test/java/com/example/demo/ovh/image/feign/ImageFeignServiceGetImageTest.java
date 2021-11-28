package com.example.demo.ovh.image.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.image.feign.model.ImageApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ImageFeignServiceGetImageTest {

    private OvhConfig ovhConfig;
    private ImageClient imageClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        imageClient = Mockito.mock(ImageClient.class);
    }

    @Test
    public void whenGetImageThenExpectImage() {

        ImageApi expected = new ImageApi();

        Mockito.when(imageClient.getImage(Mockito.anyString(), Mockito.anyString())).thenReturn(expected);

        ImageFeignServiceImpl service = new ImageFeignServiceImpl(ovhConfig, imageClient);

        Assertions.assertEquals(expected, service.getImage("imageId"));
    }
}
