package com.example.demo.awx.organization.projection;

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
public class AwxOrganizationProjectorExistsAnyTest {

    @Autowired
    private IAwxOrganizationProjection awxOrganizationProjection;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntityExistsThenExistsAnyReturnsTrue() {

        sampleBuilder.builder().awxOrganization().build();

        boolean exists = awxOrganizationProjection.existsAny();

        Assertions.assertTrue(exists);
    }

    @Test
    public void whenEntityDoesNotExistThenExistsAnyReturnsFalse() {

        boolean exists = awxOrganizationProjection.existsAny();

        Assertions.assertFalse(exists);
    }
}
