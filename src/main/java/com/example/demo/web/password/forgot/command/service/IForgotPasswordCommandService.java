package com.example.demo.web.password.forgot.command.service;

import com.example.demo.web.password.forgot.command.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.command.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.command.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.command.service.model.FetchUserIdByEmailResponse;

public interface IForgotPasswordCommandService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);

    FetchUserIdByEmailResponse getUserIdByEmail(FetchUserIdByEmailQuery query);
}
