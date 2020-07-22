package com.example.demo.web.dashboard.service;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.user.model.User;
import com.example.demo.web.dashboard.service.model.DashboardDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {

    private final ISessionUtil sessionUtil;

    @Override
    public DashboardDetailsResponse getDashboardDetails() {

        User user = sessionUtil.getCurrentUser();

        return DashboardDetailsResponse.builder()
                .emailVerified(user.getVerification().isVerified())
                .hasProjects(user.getVerification().isVerified() && true)
                .build();
    }
}
