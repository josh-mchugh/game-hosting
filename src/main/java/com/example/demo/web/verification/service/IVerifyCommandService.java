package com.example.demo.web.verification.service;

public interface IVerifyCommandService {

    void handleResendVerificationEmail(String userId);

    void handleUserVerified(String token);
}
