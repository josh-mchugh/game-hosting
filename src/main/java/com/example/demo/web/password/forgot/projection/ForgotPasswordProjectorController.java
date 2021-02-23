package com.example.demo.web.password.forgot.projection;

import com.example.demo.web.password.forgot.form.ForgotPasswordForm;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordProjectorController {

    @GetMapping("")
    public String getDefault(@RequestParam(name = "email", required = false) String email, @ModelAttribute("form") ForgotPasswordForm form) {

        if(StringUtils.isNotBlank(email)) {

            form.setEmail(email);
        }

        return "password/forgot/view-default";
    }

    @GetMapping("/success")
    public String getForgotPasswordSuccess() {

        return "password/forgot/view-success";
    }
}
