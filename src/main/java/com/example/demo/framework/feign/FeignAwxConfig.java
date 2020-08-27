package com.example.demo.framework.feign;

import com.example.demo.framework.properties.AppConfig;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignAwxConfig {


    @Bean
    public RequestInterceptor authRequestInterceptor(AppConfig appConfig) {

        return new BasicAuthRequestInterceptor(appConfig.getAwx().getUsername(), appConfig.getAwx().getPassword());
    }
}
