package com.example.demo.framework.feign;

import com.example.demo.framework.properties.OvhConfig;
import feign.RequestInterceptor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class FeignOvhConfig {

    @Bean
    public RequestInterceptor authRequestInterceptor(OvhConfig ovhConfig, RestTemplate restTemplate) {

        return requestTemplate -> {

            String url = UriComponentsBuilder.fromHttpUrl(ovhConfig.getBaseUrl())
                    .path(requestTemplate.url())
                    .toUriString();

            String timestamp = restTemplate.getForObject(String.format("%s%s", ovhConfig.getBaseUrl(), "/1.0/auth/time"), String.class);
            String signature = getSignature(ovhConfig.getAppSecret(), ovhConfig.getCustomerKey(), requestTemplate.method(), url, requestTemplate.body(), timestamp);

            requestTemplate.header("X-Ovh-Application", ovhConfig.getAppKey());
            requestTemplate.header("X-Ovh-Timestamp", timestamp);
            requestTemplate.header("X-Ovh-Signature", signature);
            requestTemplate.header("X-Ovh-Consumer", ovhConfig .getCustomerKey());
        };
    }

    private String getSignature(String appSecret, String customerKey, String method, String query, byte[] body, String timestamp) {

        String requestBody = "";

        if(body != null) {
            requestBody = new String(body);
        }

        String formatted = String.format("%s+%s+%s+%s+%s+%s", appSecret, customerKey, method, query, requestBody, timestamp);

        return String.format("$1$%s", DigestUtils.sha1Hex(formatted));
    }
}
