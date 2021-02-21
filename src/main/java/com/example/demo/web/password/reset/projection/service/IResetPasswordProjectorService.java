package com.example.demo.web.password.reset.projection.service;

import com.example.demo.web.password.reset.projection.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.projection.service.model.ExistsByRecoveryTokenResponse;

public interface IResetPasswordProjectorService {

    ExistsByRecoveryTokenResponse existsByRecoveryToken(ExistsByRecoveryTokenQuery query);
}
