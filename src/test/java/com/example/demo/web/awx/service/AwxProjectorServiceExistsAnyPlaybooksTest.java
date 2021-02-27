package com.example.demo.web.awx.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksQuery;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxProjectorServiceExistsAnyPlaybooksTest {

    @Autowired
    private IAwxProjectorService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoExceptions() {

        Assertions.assertDoesNotThrow(() -> service.existsAnyPlaybooks(null));
    }

    @Test
    public void whenPlaybooksExistsReturnTrue() {

        sampleBuilder.builder()
                .awxOrganization()
                .awxGitlabCredential()
                .awxProject()
                .awxPlaybook()
                .build();

        ExistsAnyPlaybooksResponse response = service.existsAnyPlaybooks(new ExistsAnyPlaybooksQuery());

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenPlaybooksDoesNotExistsReturnTrue() {

        ExistsAnyPlaybooksResponse response = service.existsAnyPlaybooks(new ExistsAnyPlaybooksQuery());

        Assertions.assertFalse(response.exists());
    }
}
