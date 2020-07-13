package com.example.demo.web.verification;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.service.IUserService;
import com.example.demo.web.verification.service.IVerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyController {

    private final IUserService userService;
    private final IVerifyService verifyService;
    private final ISessionUtil sessionUtil;

    @GetMapping("/{id}")
    public String getDefault(@PathVariable("id") String id, Model model) {

        boolean exists = userService.existsByVerificationToken(id);

        model.addAttribute("validToken", exists);
        model.addAttribute("authenticated", sessionUtil.isAuthenticated());

        if(exists) {

            userService.handleEmailVerification(id);
        }

        return "verify/view-default";
    }

    @ResponseBody
    @PostMapping("/resend")
    public ResponseEntity<String> postResendEmail() {

        if(sessionUtil.isAuthenticated()) {

            verifyService.handleResendVerificationEmail(sessionUtil.getCurrentUser().getId());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
