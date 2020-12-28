package com.example.demo.web.password.reset.projection;

import com.example.demo.user.projection.IUserProjector;
import com.example.demo.web.password.reset.command.model.ResetPasswordForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reset-password")
@RequiredArgsConstructor
public class ResetPasswordProjectorController {

    private final IUserProjector userProjector;

    @GetMapping("/{token}")
    public String getResetPassword(Model model, @PathVariable("token") String token) {

        if (!userProjector.existsByRecoveryToken(token)) {

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
}
