package com.example.demo.awx.feign.organization;


import com.example.demo.awx.feign.common.ListResponse;
import com.example.demo.awx.feign.organization.model.OrganizationApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "organizationClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface OrganizationClient {

    @GetMapping("/api/v2/organizations/")
    ListResponse<OrganizationApi> getOrganizations();
}
