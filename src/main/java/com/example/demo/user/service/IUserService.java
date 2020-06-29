package com.example.demo.user.service;

import com.example.demo.user.model.User;
import com.example.demo.user.service.model.UserCreateRequest;

public interface IUserService {

    User handleCreateUser(UserCreateRequest request);
}
