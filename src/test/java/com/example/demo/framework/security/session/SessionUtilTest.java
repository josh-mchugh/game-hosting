package com.example.demo.framework.security.session;

import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.IUserProjector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SessionUtilTest {

    @Test
    public void whenSessionUtilIsAuthenticatedReturnTrue() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn("test@test");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertTrue(sessionUtil.isAuthenticated());
        }
    }

    @Test
    public void whenSessionUtilIsAuthenticatedReturnFalse() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn("anonymousUser");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertFalse(sessionUtil.isAuthenticated());
        }
    }

    @Test
    public void whenSessionUtilGetCurrentUserEmailThenReturnCurrentUserEmail() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn("test@test");

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertEquals("test@test", sessionUtil.getCurrentUserEmail());
        }
    }

    @Test
    public void whenSessionUtilGetCurrentUserThenReturnCurrentUser() {

        User user = User.builder()
                .id("id")
                .build();

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);
        Mockito.when(userProjector.getUserByEmail("test@test")).thenReturn(user);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test@test");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            User expected = User.builder()
                    .id("id")
                    .build();

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertEquals(expected, sessionUtil.getCurrentUser());
        }
    }
}
