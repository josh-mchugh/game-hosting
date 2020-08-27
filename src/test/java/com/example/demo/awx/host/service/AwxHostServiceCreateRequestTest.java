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

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AwxHostServiceCreateRequestTest {

    @Autowired
    private IAwxHostService awxHostService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup () {

        data = sampleBuilder.builder()
                .user()
                .region()
                .flavor()
                .image()
                .project()
                .credential()
                .instanceGroup()
                .instance()
                .awxOrganization()
                .awxInventory()
                .awxHost()
                .build();
    }

    @Test
    public void whenCreateRequestHasNullParamThenThrowException () {

        Assertions.assertThrows(NullPointerException.class, () -> awxHostService.handleCreateRequest(null));
    }

    @Test
    public void whenCreateRequestIsValidThenReturnNotNull() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .build();
        AwxHost awxHost = awxHostService.handleCreateRequest(request);

        Assertions.assertNotNull(awxHost);
    }

    @Test
    public void whenCreateRequestIsValidThenReturnId() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .build();
        AwxHost awxHost = awxHostService.handleCreateRequest(request);

        Assertions.assertNotNull(awxHost.getId());
    }

    @Test
    public void whenCreateRequestHasNullInventoryIdThrowException() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventoryId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxHostService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasNullInstanceIdThenThrowException() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instanceId(null)
                .build();

        Assertions.assertThrows(IllegalArgumentException.class, () -> awxHostService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasHostnameThenReturnHostName() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .hostname("hostname")
                .build();
        AwxHost awxHost = awxHostService.handleCreateRequest(request);

        Assertions.assertEquals("hostname", awxHost.getHostname());
    }

    @Test
    public void whenCreateRequestHasNullHostnameThenThrowException() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .hostname(null)
                .build();

        Assertions.assertThrows(PersistenceException.class, () -> awxHostService.handleCreateRequest(request));
    }

    @Test
    public void whenCreateRequestHasDescriptionThenReturnDescription() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .description("description")
                .build();
        AwxHost awxHost = awxHostService.handleCreateRequest(request);

        Assertions.assertEquals("description", awxHost.getDescription());
    }

    @Test
    public void whenCreateRequestHasNullDescriptionThenReturnNull() {

        AwxHostCreateRequest request = TestAwxHostCreateRequest.builder()
                .inventory(data.getAwxInventory())
                .instance(data.getInstance())
                .description(null)
                .build();
        AwxHost awxHost = awxHostService.handleCreateRequest(request);

        Assertions.assertNull(awxHost.getDescription());
    }
}
