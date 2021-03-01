package com.example.demo.framework.security.authentication.service;

import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailQuery;
import com.example.demo.framework.security.authentication.service.model.FetchAuthFailureByEmailResponse;

public interface IAuthenticationFailureProjectorService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);

    FetchAuthFailureByEmailResponse fetchAuthFailureByEmail(FetchAuthFailureByEmailQuery query);
}
