package com.example.demo.awx.playbook.feign;

import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "playbookClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface IPlaybookClient {

    @GetMapping("/api/v2/projects/{projectId}/playbooks/")
    List<String> getPlaybooks(@PathVariable("projectId") Long projectId);
}
