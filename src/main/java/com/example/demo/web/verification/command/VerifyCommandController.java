package com.example.demo.web.verification.command;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyCommandController {

    private final ISessionUtil sessionUtil;
    private final CommandGateway commandGateway;

    @ResponseBody
    @PostMapping("/resend")
    public ResponseEntity<String> postResendEmail() {

        if(sessionUtil.isAuthenticated()) {

            commandGateway.send(new UserVerifyResetCommand(sessionUtil.getCurrentUser().getId()));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
