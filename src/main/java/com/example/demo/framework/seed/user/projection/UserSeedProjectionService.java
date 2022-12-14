package com.example.demo.framework.seed.user.projection;

import com.example.demo.framework.seed.user.projection.model.ExistsUserByEmailQuery;
import com.example.demo.framework.seed.user.projection.model.ExistsUserByEmailResponse;

public interface UserSeedProjectionService {

    ExistsUserByEmailResponse existsByEmail(ExistsUserByEmailQuery query);
}
