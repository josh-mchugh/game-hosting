package com.example.demo.web.password.forgot.service;

import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailResponse;

public interface ForgotPasswordQueryService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);

    FetchUserIdByEmailResponse getUserIdByEmail(FetchUserIdByEmailQuery query);
}
