package com.example.demo.web.registration.projection;

import com.example.demo.web.registration.form.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationProjectorController {

    @GetMapping("")
    public String getDefault(@ModelAttribute("form") RegistrationForm form) {

        return "registration/view-default";
    }

    @GetMapping("/success")
    public String getRegistrationSuccess() {

        return "registration/view-success";
    }
}
