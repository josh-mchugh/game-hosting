package com.example.demo.project.aggregate.saga.projection;

import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdQuery;
import com.example.demo.project.aggregate.saga.projection.model.FetchProjectRegionNameByIdResponse;

public interface IProjectBuildProjectionService {

    FetchProjectRegionNameByIdResponse fetchProjectRegionNameById(FetchProjectRegionNameByIdQuery query);
}
