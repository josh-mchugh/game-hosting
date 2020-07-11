package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;
import com.example.demo.user.service.model.UserPasswordResetRequest;

public interface IUserService {

    User handleCreateUser(UserCreateRequest request);

    Boolean existUserByEmail(String email);

    User getUserByEmail(String email);

    User handleIncrementInvalidLogin(String email);

    User handleResetInvalidLogin(String email);

    User handlePasswordReset(UserPasswordResetRequest request);
}
