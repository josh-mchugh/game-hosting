package com.example.demo.web.login;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginProjectionController {

    @GetMapping("")
    public String getLogin(Model model, @RequestParam(value = "error", required = false) Boolean hasErrors) {

        model.addAttribute("hasErrors", BooleanUtils.toBoolean(hasErrors));

        return "login/view-default";
    }
}
