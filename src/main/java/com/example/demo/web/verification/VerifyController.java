package com.example.demo.web.verification;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.user.projection.IUserProjector;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByVerificationTokenQuery;
import com.example.demo.web.verification.service.IVerifyService;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyController {

    private final IUserProjector userProjector;
    private final IVerifyService verifyService;
    private final ISessionUtil sessionUtil;
    private final CommandGateway commandGateway;

    @GetMapping("/{id}")
    public String getDefault(@PathVariable("id") String id, Model model) {

        boolean exists = userProjector.existsByVerificationToken(id);

        model.addAttribute("validToken", exists);
        model.addAttribute("authenticated", sessionUtil.isAuthenticated());

        if(exists) {

            FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(id);
            FetchUserIdByVerificationTokenProjection projection = userProjector.fetchUserIdByVerificationToken(query);

            commandGateway.send(new UserVerifyCommand(UUID.fromString(projection.getId())));
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
