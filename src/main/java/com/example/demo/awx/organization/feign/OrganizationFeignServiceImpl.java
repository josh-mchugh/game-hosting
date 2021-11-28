package com.example.demo.awx.organization.feign;

import com.example.demo.awx.feign.ListResponse;
import com.example.demo.awx.organization.feign.model.OrganizationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationFeignServiceImpl implements OrganizationFeignService {

    private final OrganizationClient organizationClient;

    @Override
    public ListResponse<OrganizationApi> getOrganizations() {

        return organizationClient.getOrganizations();
    }
}
