package com.example.demo.awx.playbook.service;

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
public class AwxPlaybookServiceExistsAnyTest {

    @Autowired
    private IAwxPlaybookService awxPlaybookService;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenEntitiesExistThenExistsAnyReturnTrue() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .awxPlaybook()
                .build();

        Assertions.assertTrue(awxPlaybookService.existsAny());
    }

    @Test
    public void whenEntitiesDoNotExistThenExistsAnyReturnsFalse() {

        Assertions.assertFalse(awxPlaybookService.existsAny());
    }
}
