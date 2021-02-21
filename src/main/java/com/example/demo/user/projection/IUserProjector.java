package com.example.demo.user.projection;

import com.example.demo.user.entity.model.User;
import com.example.demo.user.projection.model.FetchUserDashboardQuery;
import com.example.demo.user.projection.model.FetchUserIdByEmailProjection;
import com.example.demo.user.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenProjection;
import com.example.demo.user.projection.model.FetchUserIdByPasswordResetTokenQuery;
import com.example.demo.user.projection.model.FetchUserDashboardProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserProjector {

    boolean existsByEmail(String email);

    FetchUserIdByEmailProjection fetchUserIdByEmail(FetchUserIdByEmailQuery query);

    FetchUserIdByPasswordResetTokenProjection fetchUserIdByPasswordResetToken(FetchUserIdByPasswordResetTokenQuery query);

    User getUserByEmail(String email);

    Page<User> getByRecoveryTokensExpired(Pageable pageable);

    FetchUserDashboardProjection fetchUserDashboard(FetchUserDashboardQuery query);
}
