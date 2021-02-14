package com.example.demo.user.entity.model;

import com.example.demo.user.entity.VerificationStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(builderClassName = "Builder")
public class Verification {

    UUID id;
    VerificationStatus status;
    String token;
    LocalDateTime sentDate;
    LocalDateTime verificationDate;

    public boolean isVerified() {

        return VerificationStatus.VERIFIED.equals(status);
    }
}
