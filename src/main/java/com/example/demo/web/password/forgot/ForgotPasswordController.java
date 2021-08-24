package com.example.demo.web.password.forgot;

import com.example.demo.user.aggregate.command.UserRecoveryTokenCreateCommand;
import com.example.demo.web.password.forgot.form.ForgotPasswordForm;
import com.example.demo.web.password.forgot.service.ForgotPasswordQueryService;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.password.forgot.service.model.ExistsUserByEmailResponse;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailQuery;
import com.example.demo.web.password.forgot.service.model.FetchUserIdByEmailResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

    private final CommandGateway commandGateway;
    private final ForgotPasswordQueryService queryService;

    @GetMapping("")
    public String getDefault(@RequestParam(name = "email", required = false) String email, @ModelAttribute("form") ForgotPasswordForm form) {

        if(StringUtils.isNotBlank(email)) {

            form.setEmail(email);
        }

        return "password/forgot/view-default";
    }

    @PostMapping("")
    public String postForgotPassword(@Valid @ModelAttribute("form") ForgotPasswordForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "password/forgot/view-default";
        }

        ExistsUserByEmailQuery existsUserByEmailQuery = new ExistsUserByEmailQuery(form.getEmail());
        ExistsUserByEmailResponse existsUserByEmailResponse = queryService.existsByEmail(existsUserByEmailQuery);

        if(existsUserByEmailResponse.exists()) {

            FetchUserIdByEmailQuery userIdQuery = new FetchUserIdByEmailQuery(form.getEmail());
            FetchUserIdByEmailResponse userIdResponse = queryService.getUserIdByEmail(userIdQuery);

            commandGateway.send(new UserRecoveryTokenCreateCommand(userIdResponse.getId()));
        }

        return "redirect:/forgot-password/success";
    }

    @GetMapping("/success")
    public String getForgotPasswordSuccess() {

        return "password/forgot/view-success";
    }
}
