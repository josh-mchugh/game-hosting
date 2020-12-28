package com.example.demo.web.password.forgot.command;

import com.example.demo.web.password.forgot.command.model.ForgotPasswordForm;
import com.example.demo.web.password.forgot.command.service.IForgotPasswordCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordCommandController {

    private final IForgotPasswordCommandService forgotPasswordService;

    @PostMapping("")
    public String submitForgotPassword(@Valid @ModelAttribute("form") ForgotPasswordForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "password/forgot/view-default";
        }

        forgotPasswordService.handleForgotPassword(form.getEmail());

        return "redirect:/forgot-password/success";
    }
}
