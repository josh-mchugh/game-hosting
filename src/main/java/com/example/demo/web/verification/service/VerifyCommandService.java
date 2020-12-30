package com.example.demo.web.verification.service;

import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VerifyCommandService implements IVerifyCommandService {

    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @Override
    public void handleResendVerificationEmail(String userId) {

        commandGateway.send(new UserVerifyResetCommand(UUID.fromString(userId)));
    }

    @Override
    public void handleUserVerified(String token) {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(token);
        FetchUserIdByVerificationTokenProjection projection = userProjector.fetchUserIdByVerificationToken(query);

        commandGateway.send(new UserVerifyCommand(UUID.fromString(projection.getId())));
    }
}
