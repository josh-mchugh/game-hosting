package com.example.demo.awx.feign.ping;

import com.example.demo.awx.feign.ping.model.PingApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "pingClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface PingClient {

    @GetMapping("/api/v2/ping")
    PingApi getPing();
}
