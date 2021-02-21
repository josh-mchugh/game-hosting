package com.example.demo.web.password.reset.command;

import com.example.demo.user.aggregate.command.UserPasswordChangeCommand;
import com.example.demo.util.password.PasswordUtil;
import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.command.service.model.FetchUserIdByRecoveryTokenResponse;
import com.example.demo.web.password.reset.form.ResetPasswordForm;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/reset-password")
@RequiredArgsConstructor
public class ResetPasswordCommandController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/{token}")
    public String postResetPassword(@PathVariable("token") String token, Model model, @Valid @ModelAttribute("form") ResetPasswordForm form, BindingResult results) throws ExecutionException, InterruptedException {

        ValidatePasswordRequest validatePasswordRequest = new ValidatePasswordRequest(form.getPassword(), form.getConfirmPassword());
        ValidatePasswordResponse validatePasswordResponse = PasswordUtil.validatePassword(validatePasswordRequest);

        if(!validatePasswordResponse.isValid()) {

            results.rejectValue("password", validatePasswordResponse.getErrorMessageKey(), validatePasswordResponse.getErrorDefaultMessage());
        }

        if(results.hasErrors()) {

            model.addAttribute("hasValidToken", true);

            return "password/reset/view-default";
        }

        UUID userId = getUserIdByToken(token);

        UserPasswordChangeCommand command = UserPasswordChangeCommand.builder()
                .id(userId)
                .password(form.getPassword())
                .build();

        commandGateway.send(command);

        return "redirect:/reset-password/success";
    }

    private UUID getUserIdByToken(String token) throws ExecutionException, InterruptedException {

        FetchUserIdByRecoveryTokenQuery query = new FetchUserIdByRecoveryTokenQuery(token);
        FetchUserIdByRecoveryTokenResponse response = queryGateway.query(query, FetchUserIdByRecoveryTokenResponse.class).get();

        return response.getId();
    }
}
