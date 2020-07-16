package com.example.demo.web.password.reset;

import com.example.demo.recovery.service.IRecoveryTokenService;
import com.example.demo.util.PasswordUtil;
import com.example.demo.web.password.reset.model.ResetPasswordForm;
import com.example.demo.web.password.reset.service.IResetPasswordService;
import com.example.demo.web.password.reset.service.model.PasswordResetRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/reset-password")
@RequiredArgsConstructor
public class ResetPasswordController {

    private final IResetPasswordService resetPasswordService;
    private final IRecoveryTokenService recoveryTokenService;

    @GetMapping("/{id}")
    public String getResetPassword(@PathVariable("id") String id, Model model) {

        if (!recoveryTokenService.existsRecoveryToken(id)) {

            model.addAttribute("hasValidToken", false);

        }else {

            model.addAttribute("hasValidToken", true);
            model.addAttribute("form", new ResetPasswordForm());
        }

        return "password/reset/view-default";
    }

    @PostMapping("/{id}")
    public String postResetPassword(@PathVariable("id") String id, Model model, @Valid @ModelAttribute("form") ResetPasswordForm form, BindingResult results) {

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
                .recoveryTokenId(id)
                .password(form.getPassword())
                .build();

        resetPasswordService.handlePasswordReset(passwordResetRequest);

        return "redirect:/reset-password/success";
    }

    @GetMapping("/success")
    public String getPasswordResetSuccess() {

        return "password/reset/view-success";
    }
}
