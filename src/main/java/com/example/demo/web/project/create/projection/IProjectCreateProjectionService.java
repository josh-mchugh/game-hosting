package com.example.demo.web.project.create.projection;

import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableGameMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableRegionsMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableServersMapQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectAvailableServersMapResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectImageIdQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectImageIdResponse;
import com.example.demo.web.project.create.projection.model.FetchProjectStatusAndStateQuery;
import com.example.demo.web.project.create.projection.model.FetchProjectStatusAndStateResponse;

public interface IProjectCreateProjectionService {

    FetchProjectAvailableGameMapResponse fetchAvailableGameMap(FetchProjectAvailableGameMapQuery request);

    FetchProjectAvailableRegionsMapResponse fetchAvailableRegionsMap(FetchProjectAvailableRegionsMapQuery query);

    FetchProjectAvailableServersMapResponse fetchAvailableServersMap(FetchProjectAvailableServersMapQuery query);

    FetchProjectStatusAndStateResponse fetchStatusAndState(FetchProjectStatusAndStateQuery query);

    FetchProjectImageIdResponse fetchProjectImageId(FetchProjectImageIdQuery query);
}
