package com.example.demo.web.verification.projection.service;

import com.example.demo.web.verification.projection.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.projection.service.model.ExistsUserByVerifyTokenResponse;
import com.example.demo.web.verification.projection.service.model.FetchUserIdByVerificationTokenResponse;
import com.example.demo.web.verification.projection.service.model.FetchUserIdByVerificationTokenQuery;

public interface IVerifyProjectorService {

    ExistsUserByVerifyTokenResponse existsByToken(ExistsUserByVerifyTokenQuery query);

    FetchUserIdByVerificationTokenResponse fetchUserIdByVerificationToken(FetchUserIdByVerificationTokenQuery query);
}
