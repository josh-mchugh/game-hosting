package com.example.demo.web.verification.service;

import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenResponse;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenResponse;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenQuery;

public interface VerifyProjectorService {

    ExistsUserByVerifyTokenResponse existsByToken(ExistsUserByVerifyTokenQuery query);

    FetchUserIdByVerificationTokenResponse fetchUserIdByVerificationToken(FetchUserIdByVerificationTokenQuery query);
}
