package com.example.demo.framework.security.session.projection;

import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailQuery;
import com.example.demo.framework.security.session.projection.model.FetchUserIdByEmailResponse;

public interface ISessionUtilProjectorService {

    FetchUserIdByEmailResponse fetchUserIdByEmail(FetchUserIdByEmailQuery query);
}
