package com.example.demo.framework.security.session;

import java.util.UUID;

public interface ISessionUtil {

    boolean isAuthenticated();

    boolean isAdmin();

    boolean isImpersonating();

    String getCurrentUserEmail();

    UUID getCurrentUserId();
}
