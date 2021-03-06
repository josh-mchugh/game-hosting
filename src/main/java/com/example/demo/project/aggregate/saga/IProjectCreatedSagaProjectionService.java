package com.example.demo.project.aggregate.saga;

import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialQuery;
import com.example.demo.project.aggregate.saga.model.FetchAnsibleCredentialResponse;

public interface IProjectCreatedSagaProjectionService {

    FetchAnsibleCredentialResponse fetchAnsibleCredential(FetchAnsibleCredentialQuery query);
}
