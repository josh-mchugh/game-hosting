package com.example.demo.web.password.reset.service;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.aggregate.event.UserRecoveryTokenCreatedEvent;
import com.example.demo.user.entity.model.User;
import com.example.demo.user.entity.service.UserService;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenQuery;
import com.example.demo.web.password.reset.service.model.ExistsByRecoveryTokenResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.lang.reflect.UndeclaredThrowableException;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class ResetPasswordServiceExistsByTokenTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetPasswordService service;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(UndeclaredThrowableException.class, () -> service.existsByRecoveryToken(null));
    }

    @Test
    public void whenEntityExistsThenReturnTrue() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        UserRecoveryTokenCreatedEvent event = UserRecoveryTokenCreatedEvent.builder()
                .id(user.getId())
                .recoveryToken(UserRecoveryTokenCreatedEvent.createRecoveryToken(0L))
                .build();

        User updatedUser = userService.handleRecoveryTokenCreated(event);

        ExistsByRecoveryTokenQuery query = new ExistsByRecoveryTokenQuery(updatedUser.getRecoveryToken().getToken());
        ExistsByRecoveryTokenResponse response = service.existsByRecoveryToken(query);

        Assertions.assertEquals(response, new ExistsByRecoveryTokenResponse(true));
    }

    @Test
    public void whenEntityDoesNotExistThenReturnFalse() {

        ExistsByRecoveryTokenQuery query = new ExistsByRecoveryTokenQuery("token");
        ExistsByRecoveryTokenResponse response = service.existsByRecoveryToken(query);

        Assertions.assertEquals(response, new ExistsByRecoveryTokenResponse(false));
    }
}
