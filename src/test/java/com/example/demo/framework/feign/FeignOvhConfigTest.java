package com.example.demo.framework.feign;

import com.example.demo.framework.properties.OvhConfig;
import com.google.common.collect.ImmutableMap;
import feign.RequestTemplate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

public class FeignOvhConfigTest {

    @Test
    public void whenConfigHasRequestInterceptorThenReturnTestInterceptor() {

        FeignOvhConfig config = new FeignOvhConfig();

        OvhConfig ovhConfig = new OvhConfig();
        ovhConfig.setBaseUrl("https://www.baseurl.com");
        ovhConfig.setAppSecret("appSecret");
        ovhConfig.setAppKey("appKey");
        ovhConfig.setCustomerKey("ovhCustomerKey");

        Assertions.assertNotNull(config.authRequestInterceptor(ovhConfig, new RestTemplate()));
    }

    @Test
    public void whenConfigRequestInterceptorApplyThenExpectNoException() {

        FeignOvhConfig config = new FeignOvhConfig();

        OvhConfig ovhConfig = new OvhConfig();
        ovhConfig.setBaseUrl("https://www.baseurl.com");
        ovhConfig.setAppSecret("appSecret");
        ovhConfig.setAppKey("appKey");
        ovhConfig.setCustomerKey("ovhCustomerKey");

        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(LocalDateTime.now().toString());

        RequestTemplate requestTemplate = new RequestTemplate();
        requestTemplate.body(ImmutableMap.of("test", "test").toString());

        Assertions.assertDoesNotThrow(() -> config.authRequestInterceptor(ovhConfig, restTemplate).apply(requestTemplate));
    }
}
