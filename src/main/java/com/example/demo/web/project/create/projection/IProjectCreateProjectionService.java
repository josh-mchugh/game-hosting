package com.example.demo.web.project.create.projection;

import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapResponse;

public interface IProjectCreateProjectionService {

    FetchProjectAvailableGameMapResponse fetchAvailableGameMap(FetchProjectAvailableGameMapQuery request);

    FetchProjectAvailableRegionsMapResponse fetchAvailableRegionsMap(FetchProjectAvailableRegionsMapQuery query);
}
