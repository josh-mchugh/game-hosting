package com.example.demo.framework.security.session;

import com.example.demo.user.model.User;

public interface ISessionUtil {

    boolean isAuthenticated();

    User getCurrentUser();
}
