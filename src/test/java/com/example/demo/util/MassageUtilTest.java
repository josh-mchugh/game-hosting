package com.example.demo.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class MassageUtilTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void whenMessageWithKeyReturnMessage() {

        Assertions.assertEquals("Logout", messageService.getMessage("message.logout"));
    }

    @Test
    public void whenMessageWithKeyAndArgsReturnMessage() {

        String expected = "Your password reset is complete for test@test. You may sign in into your account with your new password.";

        Assertions.assertEquals(expected, messageService.getMessage("email.password.reset.body.content", "test@test"));
    }
}
