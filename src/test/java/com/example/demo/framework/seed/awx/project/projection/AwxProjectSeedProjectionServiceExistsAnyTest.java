package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectQuery;
import com.example.demo.framework.seed.awx.project.projection.model.ExistsAnyAwxProjectResponse;
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
public class AwxProjectSeedProjectionServiceExistsAnyTest {

    @Autowired
    private IAwxProjectSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenProjectExistsThenExpectExistsTrue() {

        sampleBuilder.builder().awxProject();

        ExistsAnyAwxProjectQuery query = new ExistsAnyAwxProjectQuery();
        ExistsAnyAwxProjectResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenProjectDoesNotExistThenExpectFalse() {

        ExistsAnyAwxProjectQuery query = new ExistsAnyAwxProjectQuery();
        ExistsAnyAwxProjectResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
