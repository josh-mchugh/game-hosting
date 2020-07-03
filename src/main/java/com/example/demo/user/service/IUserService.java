package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;

public interface IUserService {

    User handleCreateUser(UserCreateRequest request);

    Boolean existUserByEmail(String email);

    User getUserByEmail(String email);

    User handleIncrementInvalidLogin(String email);

    User handleResetInvalidLogin(String email);
}
