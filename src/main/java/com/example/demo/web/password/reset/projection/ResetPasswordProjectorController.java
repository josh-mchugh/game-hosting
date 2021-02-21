package com.example.demo.web.password.reset.projection;

import com.example.demo.web.password.reset.form.ResetPasswordForm;
import com.example.demo.web.password.reset.projection.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.projection.service.model.ExistsByRecoveryTokenResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/reset-password")
@RequiredArgsConstructor
public class ResetPasswordProjectorController {

    private final QueryGateway queryGateway;

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

    private boolean existsByRecoveryToken(String token) throws ExecutionException, InterruptedException {

        ExistsByRecoveryTokenQuery query = new ExistsByRecoveryTokenQuery(token);
        ExistsByRecoveryTokenResponse response = queryGateway.query(query, ExistsByRecoveryTokenResponse.class).get();

        return response.exists();
    }
}
