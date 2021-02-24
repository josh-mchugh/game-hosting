package com.example.demo.user.projection;

import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserProjector {

    boolean existsByEmail(String email);

    FetchUserIdByEmailProjection fetchUserIdByEmail(FetchUserIdByEmailQuery query);

    User getUserByEmail(String email);

    Page<User> getByRecoveryTokensExpired(Pageable pageable);
}
