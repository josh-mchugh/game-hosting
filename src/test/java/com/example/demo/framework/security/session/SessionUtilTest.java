package com.example.demo.framework.security.session;

import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailResponse;
import com.google.common.collect.Lists;
import org.axonframework.queryhandling.QueryGateway;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SessionUtilTest {

    @Test
    public void whenSessionUtilIsAuthenticatedReturnTrue() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn("test@test");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertTrue(sessionUtil.isAuthenticated());
        }
    }

    @Test
    public void whenSessionUtilIsAuthenticatedReturnFalse() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn("anonymousUser");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertFalse(sessionUtil.isAuthenticated());
        }
    }

    @Test
    public void whenSessionUtilIsAdminThenReturnIsAdminTrue() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        Collection<SimpleGrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> grantedAuthorities);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertTrue(sessionUtil.isAdmin());
        }
    }

    @Test
    public void whenSessionUtilIsNotAdminThenReturnIsAdminFalse() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> new ArrayList<>());

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertFalse(sessionUtil.isAdmin());
        }
    }

    @Test
    public void whenSessionUtilIsImpersonatingThenReturnIsImpersonatingTrue() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        Collection<SimpleGrantedAuthority> grantedAuthorities = Lists.newArrayList(new SimpleGrantedAuthority(SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> grantedAuthorities);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertTrue(sessionUtil.isImpersonating());
        }
    }

    @Test
    public void whenSessionUtilIsNotImpersonatingThenReturnIsImpersonatingFalse() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getAuthorities()).thenAnswer(invocationOnMock -> new ArrayList<>());

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertFalse(sessionUtil.isImpersonating());
        }
    }

    @Test
    public void whenSessionUtilGetCurrentUserEmailThenReturnCurrentUserEmail() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn("test@test");

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertEquals("test@test", sessionUtil.getCurrentUserEmail());
        }
    }

    @Test
    public void whenSessionUtilGetCurrentUserIdThenReturnUserId() {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);

        UUID id = UUID.randomUUID();
        Mockito.when(queryGateway.query(new FetchUserIdByEmailQuery("test@test"), FetchUserIdByEmailResponse.class))
                .thenReturn(CompletableFuture.completedFuture(new FetchUserIdByEmailResponse(id)));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test@test");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);
            Assertions.assertEquals(id, sessionUtil.getCurrentUserId());
        }
    }

    @Test
    public void whenSessionUtilGetCurrentUserAndThrowsErrorThenReturnNull() throws ExecutionException, InterruptedException {

        QueryGateway queryGateway = Mockito.mock(QueryGateway.class);
        CompletableFuture<FetchUserIdByEmailResponse> completableFuture = (CompletableFuture<FetchUserIdByEmailResponse>) Mockito.mock(CompletableFuture.class);

        Mockito.when(queryGateway.query(new FetchUserIdByEmailQuery("test@test"), FetchUserIdByEmailResponse.class))
                .thenReturn( completableFuture);
        Mockito.when(completableFuture.get()).thenThrow(new ExecutionException(new Exception("Boom")));

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("test@test");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> theMock = Mockito.mockStatic(SecurityContextHolder.class)) {

            theMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            SessionUtil sessionUtil = new SessionUtil(queryGateway);

            Assertions.assertNull(sessionUtil.getCurrentUserId());
        }
    }
}
