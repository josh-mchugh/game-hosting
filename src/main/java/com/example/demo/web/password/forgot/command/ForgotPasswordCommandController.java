package com.example.demo.web.password.forgot.command;

import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommand;
import com.example.demo.web.password.forgot.command.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.command.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.command.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.command.service.model.FetchUserIdByEmailResponse;
import com.example.demo.web.password.forgot.form.ForgotPasswordForm;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordCommandController {

    private final QueryGateway queryGateway;
    private final CommandGateway commandGateway;

    @PostMapping("")
    public String postForgotPassword(@Valid @ModelAttribute("form") ForgotPasswordForm form, BindingResult result) throws ExecutionException, InterruptedException {

        if(result.hasErrors()) {

            return "password/forgot/view-default";
        }

        if(existsByEmail(form.getEmail())) {

            UUID userId = getUserIdByEmail(form.getEmail());

            commandGateway.send(new UserRecoveryTokenCreateCommand(userId));
        }

        return "redirect:/forgot-password/success";
    }

    private boolean existsByEmail(String email) throws ExecutionException, InterruptedException {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(email);
        ExistsUserByEmailResponse response = queryGateway.query(query, ExistsUserByEmailResponse.class).get();

        return response.exists();
    }

    private UUID getUserIdByEmail(String email) throws ExecutionException, InterruptedException {

        FetchUserIdByEmailQuery query = new FetchUserIdByEmailQuery(email);
        FetchUserIdByEmailResponse response = queryGateway.query(query, FetchUserIdByEmailResponse.class).get();

        return response.getId();
    }
}
