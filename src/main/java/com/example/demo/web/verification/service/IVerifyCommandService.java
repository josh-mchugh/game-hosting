package com.example.demo.web.verification.service;

import java.util.UUID;

public interface IVerifyCommandService {

    void handleResendVerificationEmail(UUID userId);

    void handleUserVerified(String token);
}
