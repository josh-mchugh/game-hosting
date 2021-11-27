package com.example.demo.web.verification;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.aggregate.command.UserVerifyCommand;
import com.example.demo.user.aggregate.command.UserVerifyResetCommand;
import com.example.demo.web.verification.service.IVerifyProjectorService;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenQuery;
import com.example.demo.web.verification.service.model.ExistsUserByVerifyTokenResponse;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenQuery;
import com.example.demo.web.verification.service.model.FetchUserIdByVerificationTokenResponse;
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
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerificationController {

    private final ISessionUtil sessionUtil;
    private final CommandGateway commandGateway;
    private final IVerifyProjectorService verifyProjectorService;

    @GetMapping("/{id}")
    public String getDefault(@PathVariable("id") String token, Model model) throws ExecutionException, InterruptedException {

        boolean exists = existsByToken(token);

        model.addAttribute("validToken", exists);
        model.addAttribute("authenticated", sessionUtil.isAuthenticated());

        if(exists) {

            commandGateway.send(new UserVerifyCommand(getUserIdByToken(token)));
        }

        return "verification/view-default";
    }

    @ResponseBody
    @PostMapping("/resend")
    public ResponseEntity<String> postResendEmail() {

        if(sessionUtil.isAuthenticated()) {

            commandGateway.send(new UserVerifyResetCommand(sessionUtil.getCurrentUserId()));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean existsByToken(String token) {

        ExistsUserByVerifyTokenQuery query = new ExistsUserByVerifyTokenQuery(token);
        ExistsUserByVerifyTokenResponse response = verifyProjectorService.existsByToken(query);

        return response.isExist();
    }

    private UUID getUserIdByToken(String token) {

        FetchUserIdByVerificationTokenQuery query = new FetchUserIdByVerificationTokenQuery(token);
        FetchUserIdByVerificationTokenResponse response = verifyProjectorService.fetchUserIdByVerificationToken(query);

        return response.getId();
    }
}
