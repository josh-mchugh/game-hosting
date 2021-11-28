package com.example.demo.framework.seed.ovh.flavor.projection;

import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorQuery;
import com.example.demo.framework.seed.ovh.flavor.projection.model.ExistsAnyFlavorResponse;
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
public class FlavorSeedProjectionServiceExistsAnyTest {

    @Autowired
    private FlavorSeedProjectionService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectNoException() {

        Assertions.assertDoesNotThrow(() -> service.existsAny(null));
    }

    @Test
    public void whenFlavorExistsThenReturnTrue() {

        sampleBuilder.builder()
                .flavor()
                .build();

        ExistsAnyFlavorQuery query = new ExistsAnyFlavorQuery();
        ExistsAnyFlavorResponse response = service.existsAny(query);

        Assertions.assertTrue(response.exists());
    }

    @Test
    public void whenFlavorDoesNotExistThenReturnFalse() {

        ExistsAnyFlavorQuery query = new ExistsAnyFlavorQuery();
        ExistsAnyFlavorResponse response = service.existsAny(query);

        Assertions.assertFalse(response.exists());
    }
}
