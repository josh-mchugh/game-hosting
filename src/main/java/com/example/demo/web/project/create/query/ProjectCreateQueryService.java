package com.example.demo.web.project.create.query;

import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableGameMapResponse;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableRegionsMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableRegionsMapResponse;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableServersMapQuery;
import com.example.demo.web.project.create.query.model.FetchProjectAvailableServersMapResponse;
import com.example.demo.web.project.create.query.model.FetchProjectImageIdQuery;
import com.example.demo.web.project.create.query.model.FetchProjectImageIdResponse;
import com.example.demo.web.project.create.query.model.FetchProjectStatusAndStateQuery;
import com.example.demo.web.project.create.query.model.FetchProjectStatusAndStateResponse;

public interface ProjectCreateQueryService {

    FetchProjectAvailableGameMapResponse fetchAvailableGameMap(FetchProjectAvailableGameMapQuery request);

    FetchProjectAvailableRegionsMapResponse fetchAvailableRegionsMap(FetchProjectAvailableRegionsMapQuery query);

    FetchProjectAvailableServersMapResponse fetchAvailableServersMap(FetchProjectAvailableServersMapQuery query);

    FetchProjectStatusAndStateResponse fetchStatusAndState(FetchProjectStatusAndStateQuery query);

    FetchProjectImageIdResponse fetchProjectImageId(FetchProjectImageIdQuery query);
}
