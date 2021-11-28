package com.example.demo.framework.seed.ovh.image.projection;

import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageQuery;
import com.example.demo.framework.seed.ovh.image.projection.model.ExistsAnyImageResponse;
import com.example.demo.sample.SampleBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ImageSeedProjectionServiceExistsAnyTest {

    @Autowired
    private ImageSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNotException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenImageExistsThenExpectTrue() {

        sampleBuilder.builder().image().build();

        ExistsAnyImageResponse response = service.existsAny(new ExistsAnyImageQuery());

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenImageDoesNotExistsThenExpectFalse() {

        ExistsAnyImageResponse response = service.existsAny(new ExistsAnyImageQuery());

        Assertions.assertFalse(response.exists());
    }
}
