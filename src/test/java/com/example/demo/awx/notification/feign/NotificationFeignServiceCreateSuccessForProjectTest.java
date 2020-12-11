package com.example.demo.awx.notification.feign;

import com.example.demo.awx.notification.feign.model.NotificationApi;
import com.example.demo.awx.notification.feign.model.NotificationCreateApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class NotificationFeignServiceCreateSuccessForProjectTest {

    private INotificationClient notificationClient;

    @BeforeEach
    public void setup() {

        notificationClient = Mockito.mock(INotificationClient.class);
    }

    @Test
    public void whenParamsAreValidThenExpectResult() {

        NotificationApi expected = new NotificationApi();

        Mockito.when(notificationClient.createSuccessNotificationForProject(Mockito.anyLong(), Mockito.any())).thenReturn(expected);

        NotificationFeignService notificationFeignService = new NotificationFeignService(notificationClient);

        NotificationApi response = notificationFeignService.createSuccessNotificationForProject(1L, NotificationCreateApi.builder().build());

        Assertions.assertEquals(expected, response);
    }
}
