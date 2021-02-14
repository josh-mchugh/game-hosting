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
    public void handleResendVerificationEmail(UUID userId) {

        commandGateway.send(new UserVerifyResetCommand(userId));
    }

    @Override
    public void handleUserVerified(String token) {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(token);
        FetchUserIdByVerificationTokenProjection projection = userProjector.fetchUserIdByVerificationToken(query);

        commandGateway.send(new UserVerifyCommand(projection.getId()));
    }
}
