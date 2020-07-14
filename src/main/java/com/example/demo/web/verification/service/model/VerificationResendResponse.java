package com.example.demo.web.verification.service.model;

import com.example.demo.user.model.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(builderClassName = "Builder")
public class VerificationResendResponse {

    Boolean success;
    User user;
}
