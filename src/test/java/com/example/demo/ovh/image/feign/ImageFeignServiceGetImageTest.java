package com.example.demo.ovh.image.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.example.demo.ovh.image.feign.model.ImageApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ImageFeignServiceGetImageTest {

    private OvhConfig ovhConfig;
    private IImageClient imageClient;

    @BeforeEach
    public void setup() {

        ovhConfig = new OvhConfig();
        ovhConfig.setProjectId("projectId");

        imageClient = Mockito.mock(IImageClient.class);
    }

    @Test
    public void whenGetImageThenExpectImage() {

        ImageApi expected = new ImageApi();

        Mockito.when(imageClient.getImage(Mockito.anyString(), Mockito.anyString())).thenReturn(expected);

        ImageFeignService service = new ImageFeignService(ovhConfig, imageClient);

        Assertions.assertEquals(expected, service.getImage("imageId"));
    }
}
