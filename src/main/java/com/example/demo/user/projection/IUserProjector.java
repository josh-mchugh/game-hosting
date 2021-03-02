package com.example.demo.user.projection;

import com.example.demo.user.entity.model.User;

public interface IUserProjector {

    boolean existsByEmail(String email);

    User getUserByEmail(String email);
}
