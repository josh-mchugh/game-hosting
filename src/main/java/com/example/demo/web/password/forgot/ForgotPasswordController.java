package com.example.demo.web.password.forgot;

import com.example.demo.web.password.forgot.model.ForgotPasswordForm;
import com.example.demo.web.password.forgot.service.IForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final IForgotPasswordService forgotPasswordService;

    @GetMapping("")
    public String getDefault(@RequestParam(name = "email", required = false) String email, @ModelAttribute("form") ForgotPasswordForm form) {

        if(StringUtils.isNotBlank(email)) {

            form.setEmail(email);
        }

        return "password/forgot/view-default";
    }

    @PostMapping("")
    public String postDefault(@Valid @ModelAttribute("form") ForgotPasswordForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "password/forgot/view-default";
        }

        forgotPasswordService.handleForgotPassword(form.getEmail());

        return "redirect:/forgot-password/success";
    }

    @GetMapping("/success")
    public String getForgotPasswordSuccess() {

        return "password/forgot/view-success";
    }
}
