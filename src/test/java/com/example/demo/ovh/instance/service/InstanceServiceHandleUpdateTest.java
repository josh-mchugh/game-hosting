package com.example.demo.ovh.instance.service;

import com.example.demo.ovh.instance.entity.InstanceStatus;
import com.example.demo.ovh.instance.model.Instance;
import com.example.demo.ovh.instance.service.model.InstanceUpdateRequest;
import com.example.demo.sample.SampleBuilder;
import com.example.demo.sample.SampleData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class InstanceServiceHandleUpdateTest {

    @Autowired
    private IInstanceService instanceService;

    @Autowired
    private SampleBuilder sampleBuilder;

    private SampleData data;

    @BeforeEach
    public void setup() {

        data = sampleBuilder.createDefault();
    }

    @Test
    public void whenUpdateRequestIsNullThenThrowException() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleInstanceUpdate(null));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenUpdateIdIsInvalidThenThrow() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .id("invalid-id")
                .build();

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> instanceService.handleInstanceUpdate(request));

        Assertions.assertNotNull(exception);
    }

    @Test
    public void whenUpdateNameIsChangedThenReturnUpdatedName() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .name("new-name")
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals("new-name", instance.getName());
    }

    @Test
    public void whenUpdateStatusIsChangedThenReturnUpdatedStatus() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .status(InstanceStatus.STOPPED)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals(InstanceStatus.STOPPED, instance.getStatus());
    }

    @Test
    public void whenUpdateCreateDateThenReturnUpdatedCreatedDate() {

        LocalDateTime createdDate = LocalDateTime.now();

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .instanceCreatedDate(createdDate)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals(createdDate, instance.getInstanceCreatedDate());
    }

    @Test
    public void whenUpdateIp4AddressThenReturnUpdatedIp4Address() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address("1.1.1.1.1.1")
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals("1.1.1.1.1.1", instance.getIp4Address());
    }

    @Test
    public void whenUpdateIp6AddressThenReturnUpdatedIp6Address() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address("1.1.1.1.1.1")
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertEquals("1.1.1.1.1.1", instance.getIp4Address());
    }

    @Test
    public void whenUpdateNameIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .name(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull( instance.getName());
    }

    @Test
    public void whenUpdateStatusIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .status(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull(instance.getStatus());
    }

    @Test
    public void whenUpdateCreateDateIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .instanceCreatedDate(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull(instance.getInstanceCreatedDate());
    }

    @Test
    public void whenUpdateIp4AddressIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull( instance.getIp4Address());
    }

    @Test
    public void whenUpdateIp6AddressIsNullThenReturnNull() {

        InstanceUpdateRequest request = getInstanceUpdateBuilder()
                .ip4Address(null)
                .build();

        Instance instance = instanceService.handleInstanceUpdate(request);

        Assertions.assertNull(instance.getIp4Address());
    }

    private InstanceUpdateRequest.Builder getInstanceUpdateBuilder() {

        return InstanceUpdateRequest.builder()
                .id(data.getInstance().getId())
                .name(data.getInstance().getName())
                .status(data.getInstance().getStatus())
                .instanceCreatedDate(data.getInstance().getInstanceCreatedDate())
                .ip4Address(data.getInstance().getIp4Address())
                .ip6Address(data.getInstance().getIp6Address());
    }
}
