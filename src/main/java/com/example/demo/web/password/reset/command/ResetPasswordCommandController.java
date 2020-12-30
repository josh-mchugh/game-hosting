package com.example.demo.web.password.reset.command;

import com.example.demo.util.password.PasswordUtil;
import com.example.demo.web.password.reset.command.model.ResetPasswordForm;
import com.example.demo.web.password.reset.command.service.IResetPasswordService;
import com.example.demo.web.password.reset.command.service.model.PasswordResetRequest;
import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
@RequiredArgsConstructor
public class ResetPasswordCommandController {

    private final IResetPasswordService resetPasswordService;

    @PostMapping("/{token}")
    public String postResetPassword(@PathVariable("token") String token, Model model, @Valid @ModelAttribute("form") ResetPasswordForm form, BindingResult results) {

        ValidatePasswordRequest validatePasswordRequest = new ValidatePasswordRequest(form.getPassword(), form.getConfirmPassword());
        ValidatePasswordResponse validatePasswordResponse = PasswordUtil.validatePassword(validatePasswordRequest);

        if(!validatePasswordResponse.isValid()) {

            results.rejectValue("password", validatePasswordResponse.getErrorMessageKey(), validatePasswordResponse.getErrorDefaultMessage());
        }

        if(results.hasErrors()) {

            model.addAttribute("hasValidToken", true);

            return "password/reset/view-default";
        }

        PasswordResetRequest passwordResetRequest = PasswordResetRequest.builder()
                .token(token)
                .password(form.getPassword())
                .build();

        resetPasswordService.handlePasswordReset(passwordResetRequest);

        return "redirect:/reset-password/success";
    }
}
