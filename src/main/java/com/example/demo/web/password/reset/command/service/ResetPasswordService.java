package com.example.demo.web.password.reset.command.service;

import com.example.demo.user.aggregate.command.UserPasswordChangeCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenQuery;
import com.example.demo.web.password.reset.command.service.model.PasswordResetRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ResetPasswordService implements IResetPasswordService {

    private final IUserProjector userProjector;
    private final CommandGateway commandGateway;

    @Override
    public void handlePasswordReset(PasswordResetRequest request) {

        FetchUserIdByPasswordResetTokenQuery query = new FetchUserIdByPasswordResetTokenQuery(request.getToken());
        FetchUserIdByPasswordResetTokenProjection projection = userProjector.fetchUserIdByPasswordResetToken(query);

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(UUID.fromString(projection.getId()))
                .password(request.getPassword())
                .build();

         commandGateway.send(command);
    }
}
