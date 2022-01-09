package com.example.demo.awx.host.service;

import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.host.service.model.AwxHostDisableRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
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
public class AwxHostServiceDisabledTest {

    @Autowired
    private AwxHostService awxHostService;

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
    public void whenEntityEnabledIsTrueThenReturnEnabledFalse() {

        AwxHostCreateRequest createRequest = AwxHostCreateRequest.builder()
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
        AwxHost awxHost = awxHostService.handleCreate(createRequest);

        AwxHostDisableRequest disableRequest = new AwxHostDisableRequest(awxHost.getId());
        AwxHost updatedHost = awxHostService.handleDisable(disableRequest);

        Assertions.assertTrue(awxHost.getEnabled());
        Assertions.assertFalse(updatedHost.getEnabled());
    }

    @Test
    public void whenEntityEnabledIsFalseThenReturnEnabledFalse() {

        AwxHostCreateRequest createRequest = AwxHostCreateRequest.builder()
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(false)
                .build();
        AwxHost awxHost = awxHostService.handleCreate(createRequest);

        AwxHostDisableRequest disableRequest = new AwxHostDisableRequest(awxHost.getId());
        AwxHost updatedHost = awxHostService.handleDisable(disableRequest);

        Assertions.assertFalse(awxHost.getEnabled());
        Assertions.assertFalse(updatedHost.getEnabled());
    }

    @Test
    public void whenHandleDisableHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleDisable(null));
    }

    @Test
    public void whenHandleDisableHasInvalidHostIdThenThrowException() {

        AwxHostDisableRequest request = new AwxHostDisableRequest("invalidId");

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleDisable(request));
    }
}
