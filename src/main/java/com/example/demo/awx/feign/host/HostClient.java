package com.example.demo.awx.feign.host;

import com.example.demo.awx.feign.host.model.HostApi;
import com.example.demo.awx.feign.host.model.HostCreateApi;
import com.example.demo.awx.feign.host.model.HostPatchApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hostClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface HostClient {

    @PostMapping("/api/v2/hosts/")
    HostApi createHost(@RequestBody HostCreateApi body);

    @PatchMapping("/api/v2/hosts/{id}/")
    HostApi updateHost(@PathVariable("id") Long id, @RequestBody HostPatchApi body);
}
