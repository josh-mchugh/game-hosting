package com.example.demo.awx.organization.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.organization.feign.model.OrganizationApi;

public interface OrganizationFeignService {

    ListResponse<OrganizationApi> getOrganizations();
}
