package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthSuccessByEmailResponse;

public interface AuthenticationSuccessProjectorService {

    FetchAuthSuccessByEmailResponse getUserAuthByEmail(FetchAuthSuccessByEmailQuery query);
}
