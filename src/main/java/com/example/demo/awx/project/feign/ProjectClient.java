package com.example.demo.awx.project.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.project.feign.model.ProjectApi;
import com.example.demo.awx.project.feign.model.ProjectCreateApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "projectClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface ProjectClient {

    @GetMapping("/api/v2/organizations/{organizationId}/projects/")
    ListResponse<ProjectApi> getProjects(@PathVariable("organizationId") Long organizationId);

    @PostMapping("/api/v2/organizations/{organizationId}/projects/")
    ProjectApi createProject(@PathVariable("organizationId") Long organizationId, @RequestBody ProjectCreateApi body);
}
