package com.example.demo.web.registration;

import com.example.demo.user.service.IUserService;
import com.example.demo.util.PasswordUtil;
import com.example.demo.web.registration.model.RegistrationForm;
import com.example.demo.web.registration.service.IRegistrationService;
import com.example.demo.web.registration.service.model.RegistrationCreateUserRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordRequest;
import com.example.demo.web.registration.service.model.ValidatePasswordResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final IUserService userService;
    private final IRegistrationService registrationService;

    @GetMapping("")
    public String getDefault(@ModelAttribute("form") RegistrationForm form) {

        return "registration/view-default";
    }

    @PostMapping("")
    public String postDefault(Model model, @Valid @ModelAttribute("form") RegistrationForm form, BindingResult results) {

        if(StringUtils.isNotEmpty(form.getEmail())) {

            if (userService.existsUserByEmail(form.getEmail())) {

                results.rejectValue("email", "", "Email address already exists");
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

    @GetMapping("/success")
    public String getRegistrationSuccess() {

        return "registration/view-success";
    }
}
