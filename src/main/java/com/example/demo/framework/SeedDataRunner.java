package com.example.demo.framework;

import com.example.demo.user.entity.UserState;
import com.example.demo.user.entity.UserType;
import com.example.demo.user.service.IUserService;
import com.example.demo.user.service.model.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataRunner implements ApplicationRunner {

    private final IUserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info("Initializing seed data...");

        if(!userService.existsUserByEmail("admin@admin.com")) {

            log.info("Creating admin user: {}", "admin@admin.com");
            userService.handleCreateUser(createAdminUser());
        }

        log.info("Initialization of seed data complete.");
    }

    private UserCreateRequest createAdminUser() {

        return UserCreateRequest.builder()
                .email("admin@admin.com")
                .password("Password1")
                .state(UserState.ACTIVE)
                .type(UserType.ADMIN)
                .build();
    }
}
