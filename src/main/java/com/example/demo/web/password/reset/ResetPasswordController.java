package com.example.demo.web.password.reset;

import com.example.demo.user.aggregate.command.UserPasswordChangeCommand;
import com.example.demo.util.password.PasswordUtil;
import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.FetchUserIdByRecoveryTokenResponse;
import com.example.demo.web.password.reset.form.ResetPasswordForm;
import com.example.demo.web.password.reset.service.IResetPasswordService;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ResetPasswordController {

    private final IResetPasswordService resetPasswordProjectorService;
    private final CommandGateway commandGateway;

    @GetMapping("/{token}")
    public String getResetPassword(Model model, @PathVariable("token") String token) throws ExecutionException, InterruptedException {

        if (!existsByRecoveryToken(token)) {

            model.addAttribute("hasValidToken", false);

        }else {

            model.addAttribute("hasValidToken", true);
            model.addAttribute("form", new ResetPasswordForm());
        }

        return "password/reset/view-default";
    }

    @GetMapping("/success")
    public String getPasswordResetSuccess() {

        return "password/reset/view-success";
    }

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

    private boolean existsByRecoveryToken(String token) {

        ExistsByRecoveryTokenQuery query = new ExistsByRecoveryTokenQuery(token);
        ExistsByRecoveryTokenResponse response = resetPasswordProjectorService.existsByRecoveryToken(query);

        return response.exists();
    }

    private UUID getUserIdByToken(String token) {

        FetchUserIdByRecoveryTokenQuery query = new FetchUserIdByRecoveryTokenQuery(token);
        FetchUserIdByRecoveryTokenResponse response = resetPasswordProjectorService.getUserIdByToken(query);

        return response.getId();
    }
}
