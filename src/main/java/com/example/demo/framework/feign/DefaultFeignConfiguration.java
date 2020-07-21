package com.example.demo.framework.feign;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultFeignConfiguration {

    @Bean
    public OkHttpClient httpClient() {

        return new OkHttpClient(okHttp3Client());
    }

    private okhttp3.OkHttpClient okHttp3Client() {

        return new okhttp3.OkHttpClient();
    }
}
