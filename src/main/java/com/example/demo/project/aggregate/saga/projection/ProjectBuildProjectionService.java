package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchAnsibleCredentialResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectCreateInstanceDetailsResponse;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;

public interface ProjectBuildProjectionService {

    FetchProjectRegionNameByIdResponse fetchProjectRegionNameById(FetchProjectRegionNameByIdQuery query);

    FetchProjectCreateInstanceDetailsResponse fetchProjectCreateInstanceDetails(FetchProjectCreateInstanceDetailsQuery query);

    FetchAnsibleCredentialResponse fetchAnsibleCredential(FetchAnsibleCredentialQuery query);
}
