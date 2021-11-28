package com.example.demo.web.registration;

import com.example.demo.user.aggregate.command.UserCreateRegularCommand;
import com.example.demo.util.password.PasswordUtil;
import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import com.example.demo.web.registration.form.RegistrationForm;
import com.example.demo.web.registration.service.RegistrationService;
import com.example.demo.web.registration.service.model.ExistsUserByEmailQuery;
import com.example.demo.web.registration.service.model.ExistsUserByEmailResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService service;
    private final CommandGateway commandGateway;

    @GetMapping("")
    public String getDefault(@ModelAttribute("form") RegistrationForm form) {

        return "registration/view-default";
    }

    @GetMapping("/success")
    public String getRegistrationSuccess() {

        return "registration/view-success";
    }

    @PostMapping("")
    public String postDefault(Model model, @Valid @ModelAttribute("form") RegistrationForm form, BindingResult results) {

        if(StringUtils.isNotEmpty(form.getEmail())) {

            if (existsByEmail(form.getEmail())) {

                results.rejectValue("email", "error.email.exists", "Email address already exists");
            }
        }

        ValidatePasswordRequest validatePasswordRequest = new ValidatePasswordRequest(form.getPassword(), form.getConfirmPassword());
        ValidatePasswordResponse validatePasswordResponse = PasswordUtil.validatePassword(validatePasswordRequest);

        if(!validatePasswordResponse.isValid()) {

            results.rejectValue("password", validatePasswordResponse.getErrorMessageKey(), validatePasswordResponse.getErrorDefaultMessage());
        }

        if(results.hasErrors()) {

            return "registration/view-default";
        }

        UserCreateRegularCommand command = UserCreateRegularCommand.builder()
                .id(UUID.randomUUID())
                .email(form.getEmail())
                .password(form.getPassword())
                .build();

        commandGateway.send(command);

        return "redirect:/registration/success";
    }

    private boolean existsByEmail(String email) {

        ExistsUserByEmailQuery query = new ExistsUserByEmailQuery(email);
        ExistsUserByEmailResponse response = service.existsByEmail(query);

        return response.exists();
    }
}
