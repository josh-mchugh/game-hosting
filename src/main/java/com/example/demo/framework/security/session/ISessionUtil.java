package com.example.demo.framework.security.session;

import com.example.demo.user.entity.model.User;

public interface ISessionUtil {

    boolean isAuthenticated();

    boolean isAdmin();

    boolean isImpersonating();

    String getCurrentUserEmail();

    User getCurrentUser();
}
