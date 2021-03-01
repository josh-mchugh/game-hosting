package com.example.demo.user.projection;

import com.example.demo.user.entity.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserProjector {

    boolean existsByEmail(String email);

    User getUserByEmail(String email);

    Page<User> getByRecoveryTokensExpired(Pageable pageable);
}
