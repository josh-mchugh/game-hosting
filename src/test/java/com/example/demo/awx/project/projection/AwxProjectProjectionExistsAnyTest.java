package com.example.demo.awx.project.projection;

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
public class AwxProjectProjectionExistsAnyTest {

    @Autowired
    private IAwxProjectProjector awxProjectProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntitiesExistThenExistsAnyReturnTrue() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .awxProject()
                .build();

        Assertions.assertTrue(awxProjectProjector.existsAny());
    }

    @Test
    public void whenEntityDoesNotExitThenExitsAnyReturnFalse() {

        Assertions.assertFalse(awxProjectProjector.existsAny());
    }
}
