package com.example.demo.framework;

import com.example.demo.email.entity.EmailTemplate;
import com.example.demo.email.service.IEmailService;
import com.example.demo.email.service.model.EmailCreateRequest;
import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataRunner implements ApplicationRunner {

    private final IUserService userService;
    private final IEmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Initializing seed data...");

        if(!userService.existUserByEmail("admin@admin.com")) {

            log.info("Creating admin user: {}", "admin@admin.com");
            userService.handleCreateUser(createAdminUser());
        }

        if(!emailService.existsPendingEmails()) {

            log.info("Creating pending emails");
            createPendingEmails();
        }

        log.info("Initialization of seed data complete.");
    }

    private UserCreateRequest createAdminUser() {

        return UserCreateRequest.builder()
                .email("admin@admin.com")
                .password(passwordEncoder.encode("Password1"))
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();
    }

    private void createPendingEmails() {

        for(int i = 0; i < 40; i++) {

            EmailCreateRequest request = EmailCreateRequest.builder()
                    .template(EmailTemplate.TEST)
                    .toAddress(String.format("test%s@test%s.com", i, i))
                    .context("name", String.format("name%s", i))
                    .build();

            emailService.handleCreateEmail(request);
        }
    }
}
