package com.example.demo.awx.host.service;

import com.example.demo.awx.host.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import com.example.demo.sample.util.TestAwxHostCreateRequest;
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
public class AwxHostServiceHandleEnabledTest {

    @Autowired
    private IAwxHostService awxHostService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.builder()
                .region()
                .flavor()
                .image()
                .credential()
                .user()
                .project()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxInventory()
                .build();
    }

    @Test
    public void whenHandleEnableIsFalseThenEnableReturnTrue() {

        AwxHostCreateRequest createRequest = TestAwxHostCreateRequest.builder()
            .inventory(data.getAwxInventory())
            .instance(data.getInstance())
            .enabled(false)
            .build();

        AwxHost awxHost = awxHostService.handleCreateRequest(createRequest);

        AwxHost updatedHost = awxHostService.handleEnabledRequest(awxHost.getHostId());

        Assertions.assertTrue(updatedHost.getEnabled());
    }

    @Test
    public void whenHandleEnableIsTrueThenEnableReturnTrue() {

        AwxHostCreateRequest createRequest = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .enabled(true)
                .build();

        AwxHost awxHost = awxHostService.handleCreateRequest(createRequest);

        AwxHost updatedHost = awxHostService.handleEnabledRequest(awxHost.getHostId());

        Assertions.assertTrue(updatedHost.getEnabled());
    }

    @Test
    public void whenHandleEnableHasNullParamThenThrowException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxHostService.handleEnabledRequest(null));
    }

    @Test
    public void whenHandleEnableHasInvalidHostIdThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleEnabledRequest(2L));
    }
}
