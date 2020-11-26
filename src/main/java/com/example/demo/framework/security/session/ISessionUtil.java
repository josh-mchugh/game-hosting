package com.example.demo.framework.security.session;

import com.example.demo.user.entity.model.User;

public interface ISessionUtil {

    boolean isAuthenticated();

    String getCurrentUserEmail();

    User getCurrentUser();
}
