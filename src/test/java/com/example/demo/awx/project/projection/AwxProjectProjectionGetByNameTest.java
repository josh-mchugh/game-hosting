package com.example.demo.awx.project.projection;

import com.example.demo.awx.project.aggregate.event.AwxProjectCreatedEvent;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.awx.project.entity.service.IAwxProjectService;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectProjectionGetByNameTest {

    @Autowired
    private IAwxProjectService awxProjectService;

    @Autowired
    private IAwxProjectProjector awxProjectProjector;

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

        AwxProjectCreatedEvent event = AwxProjectCreatedEvent.builder()
                .id(UUID.randomUUID())
                .awxOrganizationId(sampleData.getAwxOrganization().getId())
                .awxCredentialId(sampleData.getAwxCredential().getId())
                .awxId(1L)
                .name("test name")
                .description("description")
                .scmType("git")
                .scmBranch("master")
                .scmUrl("url")
                .build();

        AwxProject awxProject = awxProjectService.handleCreated(event);

        AwxProject retrievedProject = awxProjectProjector.getByProjectId(awxProject.getAwxId());

        Assertions.assertEquals(awxProject, retrievedProject);
    }

    @Test
    public void whenEntityDoesNotExistsThenGetByNameReturnNull() {

        AwxProject awxCredential = awxProjectProjector.getByProjectId(1L);

        Assertions.assertNull(awxCredential);
    }

    @Test
    public void whenRequestParaIsNullThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxProjectProjector.getByProjectId(null));
    }
}
