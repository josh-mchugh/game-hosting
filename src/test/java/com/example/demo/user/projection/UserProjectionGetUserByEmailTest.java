package com.example.demo.user.projection;

import com.example.demo.sample.SampleBuilder;
import com.example.demo.user.entity.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class UserProjectionGetUserByEmailTest {

    @Autowired
    private IUserProjector userProjector;

    @Autowired
    private SampleBuilder sampleBuilder;

    @Test
    public void whenParamIsNullThenExpectException() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> userProjector.getUserByEmail(null));
    }

    @Test
    public void whenEntityExistsThenExpectNotNull() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        Assertions.assertNotNull(userProjector.getUserByEmail(user.getEmail()));
    }

    @Test
    public void whenEntityExistsThenExpectMatchingEmail() {

        User user = sampleBuilder.builder()
                .user()
                .build()
                .getUser();

        User fetchedUser = userProjector.getUserByEmail(user.getEmail());

        Assertions.assertEquals(user.getEmail(), fetchedUser.getEmail());
    }

    @Test
    public void whenEntityDoesNotExistThenReturnNull() {

        Assertions.assertNull(userProjector.getUserByEmail("test@test"));
    }
}
