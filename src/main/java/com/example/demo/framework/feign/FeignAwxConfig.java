package com.example.demo.framework.feign;

import com.example.demo.framework.properties.AwxConfig;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignAwxConfig {


    @Bean
    public RequestInterceptor authRequestInterceptor(AwxConfig awxConfig) {

        return new BasicAuthRequestInterceptor(awxConfig.getUsername(), awxConfig.getPassword());
    }
}
