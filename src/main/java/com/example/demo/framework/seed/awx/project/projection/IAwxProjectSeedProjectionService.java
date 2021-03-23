package com.example.demo.framework.seed.awx.project.projection;

import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameQuery;
import com.example.demo.framework.seed.awx.project.projection.model.FetchAwxCredentialByNameResponse;

public interface IAwxProjectSeedProjectionService {

    FetchAwxCredentialByNameResponse fetchAwxCredentialIdByName(FetchAwxCredentialByNameQuery query);
}
