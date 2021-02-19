package com.example.demo.web.verification.projection;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.web.verification.projection.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.projection.service.model.ExistsUserByVerifyTokenResponse;
import com.example.demo.web.verification.projection.service.model.FetchUserIdByVerificationTokenQuery;
import com.example.demo.web.verification.projection.service.model.FetchUserIdByVerificationTokenResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyProjectorController {

    private final ISessionUtil sessionUtil;
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public String getDefault(@PathVariable("id") String token, Model model) throws ExecutionException, InterruptedException {

        boolean exists = existsByToken(token);

        model.addAttribute("validToken", exists);
        model.addAttribute("authenticated", sessionUtil.isAuthenticated());

        if(exists) {

            commandGateway.send(new UserVerifyCommand(getUserIdByToken(token)));
        }

        return "verification/view-default";
    }

    private boolean existsByToken(String token) throws ExecutionException, InterruptedException {

        ExistsUserByVerifyTokenQuery query = new ExistsUserByVerifyTokenQuery(token);
        ExistsUserByVerifyTokenResponse response = queryGateway.query(query, ExistsUserByVerifyTokenResponse.class).get();

        return response.isExist();
    }

    private UUID getUserIdByToken(String token) throws ExecutionException, InterruptedException {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(token);
        FetchUserIdByVerificationTokenResponse response = queryGateway.query(query, FetchUserIdByVerificationTokenResponse.class).get();

        return response.getId();
    }
}
