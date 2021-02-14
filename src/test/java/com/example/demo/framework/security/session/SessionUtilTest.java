package com.example.demo.framework.security.session;

import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.IUserProjector;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

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
    public void whenSessionUtilIsAdminThenReturnIsAdminTrue() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        Collection<SimpleGrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> grantedAuthorities);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertTrue(sessionUtil.isAdmin());
        }
    }

    @Test
    public void whenSessionUtilIsNotAdminThenReturnIsAdminFalse() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> new ArrayList<>());

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertFalse(sessionUtil.isAdmin());
        }
    }

    @Test
    public void whenSessionUtilIsImpersonatingThenReturnIsImpersonatingTrue() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        Collection<SimpleGrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> grantedAuthorities);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertTrue(sessionUtil.isImpersonating());
        }
    }

    @Test
    public void whenSessionUtilIsNotImpersonatingThenReturnIsImpersonatingFalse() {

        IUserProjector userProjector = Mockito.mock(IUserProjector.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> new ArrayList<>());

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertFalse(sessionUtil.isImpersonating());
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

        UUID userId = UUID.randomUUID();

        User user = User.builder()
                .id(userId)
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
                    .id(userId)
                    .build();

            SessionUtil sessionUtil = new SessionUtil(userProjector);
            Assertions.assertEquals(expected, sessionUtil.getCurrentUser());
        }
    }
}
