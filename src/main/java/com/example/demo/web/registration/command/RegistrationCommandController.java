package com.example.demo.web.registration.command;

import com.example.demo.user.projection.IUserProjector;
import com.example.demo.util.password.PasswordUtil;
import com.example.demo.web.registration.form.RegistrationForm;
import com.example.demo.web.registration.command.service.IRegistrationCommandService;
import com.example.demo.web.registration.command.service.model.RegistrationCreateUserRequest;
import com.example.demo.util.password.model.ValidatePasswordRequest;
import com.example.demo.util.password.model.ValidatePasswordResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationCommandController {

    private final IUserProjector userProjector;
    private final IRegistrationCommandService registrationService;

    @PostMapping("")
    public String postDefault(Model model, @Valid @ModelAttribute("form") RegistrationForm form, BindingResult results) {

        if(StringUtils.isNotEmpty(form.getEmail())) {

            if (userProjector.existsByEmail(form.getEmail())) {

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

        RegistrationCreateUserRequest request = RegistrationCreateUserRequest.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .build();

        registrationService.handleCreateNewUser(request);

        return "redirect:/registration/success";
    }
}
