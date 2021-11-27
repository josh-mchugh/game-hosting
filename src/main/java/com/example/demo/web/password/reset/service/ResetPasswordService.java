package com.example.demo.web.password.reset.service;

import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenResponse;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenResponse;

public interface ResetPasswordService {

    ExistsByRecoveryTokenResponse existsByRecoveryToken(ExistsByRecoveryTokenQuery query);

    FetchUserIdByRecoveryTokenResponse getUserIdByToken(FetchUserIdByRecoveryTokenQuery query);
}
