package com.example.demo.awx.template.service;

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
public class AwxTemplateServiceExistsAnyTest {

    @Autowired
    private IAwxTemplateService awxTemplateService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntitiesExistThenExistsAnyReturnTrue() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxCredential()
                .awxInventory()
                .awxProject()
                .awxPlaybook()
                .awxTemplate()
                .build();

        Assertions.assertTrue(awxTemplateService.existsAny());
    }

    @Test
    public void whenEntitiesDoesNotExistThenExistsAnyReturnsFalse() {

        Assertions.assertFalse(awxTemplateService.existsAny());
    }
}
