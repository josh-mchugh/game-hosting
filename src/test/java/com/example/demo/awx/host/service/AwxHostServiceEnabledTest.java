package com.example.demo.awx.host.service;

import com.example.demo.awx.host.entity.model.AwxHost;
import com.example.demo.awx.host.service.model.AwxHostCreateRequest;
import com.example.demo.awx.host.service.model.AwxHostEnableRequest;
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
public class AwxHostServiceEnabledTest {

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
    public void whenEntityEnabledIsFalseThenReturnEnabledTrue() {

        AwxHostCreateRequest createRequest = AwxHostCreateRequest.builder()
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(false)
                .build();
        AwxHost awxHost = awxHostService.handleCreate(createRequest);

        AwxHostEnableRequest enableRequest = new AwxHostEnableRequest(awxHost.getId());
        AwxHost updatedHost = awxHostService.handleEnable(enableRequest);

        Assertions.assertFalse(awxHost.getEnabled());
        Assertions.assertTrue(updatedHost.getEnabled());
    }

    @Test
    public void whenEntityEnabledIsTrueThenReturnEnabledTrue() {

        AwxHostCreateRequest createRequest = AwxHostCreateRequest.builder()
                .awxInventoryId(data.getAwxInventory().getId())
                .instanceId(data.getInstance().getId())
                .awxId(1L)
                .hostname("hostname")
                .description("description")
                .enabled(true)
                .build();
        AwxHost awxHost = awxHostService.handleCreate(createRequest);

        AwxHostEnableRequest enableRequest = new AwxHostEnableRequest(awxHost.getId());
        AwxHost updatedHost = awxHostService.handleEnable(enableRequest);

        Assertions.assertTrue(awxHost.getEnabled());
        Assertions.assertTrue(updatedHost.getEnabled());
    }

    @Test
    public void whenHandleEnableHasNullParamThenThrowException() {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleEnable(null));
    }

    @Test
    public void whenHandleEnableHasInvalidHostIdThenThrowException() {

        AwxHostEnableRequest request = new AwxHostEnableRequest("invalidId");

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleEnable(request));
    }
}
