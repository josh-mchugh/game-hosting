package com.example.demo.web.project.projection.service;

import com.example.demo.web.project.projection.service.model.FetchAwxHostByInstanceOvhIdQuery;
import com.example.demo.web.project.projection.service.model.FetchAwxHostByInstanceOvhIdResponse;
import com.example.demo.web.project.projection.service.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.projection.service.model.FetchProjectDetailsResponse;

public interface IProjectProjectorService {

    FetchProjectDetailsResponse getProjectDetails(FetchProjectDetailsQuery query);

    FetchAwxHostByInstanceOvhIdResponse fetchAwxHostByInstanceId(FetchAwxHostByInstanceOvhIdQuery query);
}
