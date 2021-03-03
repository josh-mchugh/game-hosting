package com.example.demo.framework.security.user.projection;

import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.security.user.projection.model.ExistsUserByEmailResponse;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailQuery;
import com.example.demo.framework.security.user.projection.model.FetchUserDetailsByEmailResponse;

public interface IUserDetailsProjectorService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);

    FetchUserDetailsByEmailResponse fetchUserDetails(FetchUserDetailsByEmailQuery query);
}
