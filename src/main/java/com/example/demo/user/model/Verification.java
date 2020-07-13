package com.example.demo.user.model;

import com.example.demo.user.entity.VerificationStatus;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(builderClassName = "Builder")
public class Verification {

    String id;
    VerificationStatus status;
    String token;
    LocalDateTime sendDate;
    LocalDateTime verificationDate;

    public boolean isVerified() {

        return VerificationStatus.VERIFIED.equals(status);
    }
}
