package com.example.demo.awx.project.service;

import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.model.AwxProjectCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.sample.util.TestAwxProjectCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectServiceGetByNameTest {

    @Autowired
    private IAwxProjectService awxProjectService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData sampleData;

    @BeforeEach
    public void setup() {

        sampleData = sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .build();
    }

    @Test
    public void whenEntityExistsThenGetByNameReturnNotNull() {

        AwxProjectCreateRequest request = TestAwxProjectCreateRequest.builder()
                .awxOrganizationId(sampleData.getAwxOrganization().getOrganizationId())
                .awxCredentialId(sampleData.getAwxCredential().getCredentialId())
                .name("test name")
                .build();
        awxProjectService.handleCreateRequest(request);

        AwxProject awxProject = awxProjectService.getByName("test name");

        Assertions.assertNotNull(awxProject);
    }

    @Test
    public void whenEntityDoesNotExistsThenGetByNameReturnNull() {

        AwxProject awxCredential = awxProjectService.getByName("name");

        Assertions.assertNull(awxCredential);
    }

    @Test
    public void whenRequestParaIsNullThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxProjectService.getByName(null));
    }
}
