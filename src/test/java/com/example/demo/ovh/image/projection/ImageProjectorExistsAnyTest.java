package com.example.demo.ovh.image.projection;

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
public class ImageProjectorExistsAnyTest {

    @Autowired
    private SampleBuilder sampleBuilder;

    @Autowired
    private IImageProjector imageProjector;

    @Test
    public void whenImageDoesNotExistThenReturnFalse() {

        boolean exists = imageProjector.existsAny();

        Assertions.assertFalse(exists);
    }

    @Test
    public void whenImageDoesExistThenReturnTrue() {

        sampleBuilder.builder()
                .region()
                .image()
                .build();

        boolean exists = imageProjector.existsAny();

        Assertions.assertTrue(exists);
    }
}
