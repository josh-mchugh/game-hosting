package com.example.demo.web.verification.service;

import com.example.demo.web.verification.service.model.VerificationResendResponse;

public interface IVerifyService {

    VerificationResendResponse handleResendVerificationEmail(String userId);
}
