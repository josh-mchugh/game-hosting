package com.example.demo.web.verification.service;

import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerifyService implements IVerifyService {

    private final CommandGateway commandGateway;

    @Override
    public void handleResendVerificationEmail(String id) {

        commandGateway.send(new UserVerifyResetCommand(UUID.fromString(id)));
    }
}
