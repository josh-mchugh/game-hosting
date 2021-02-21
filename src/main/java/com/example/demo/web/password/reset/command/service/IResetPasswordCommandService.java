package com.example.demo.web.password.reset.command.service;

import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenResponse;

public interface IResetPasswordCommandService {

    FetchUserIdByRecoveryTokenResponse getUserIdByToken(FetchUserIdByRecoveryTokenQuery query);
}
