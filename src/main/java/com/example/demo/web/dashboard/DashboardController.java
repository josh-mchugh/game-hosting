package com.example.demo.web.dashboard;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.web.dashboard.service.DashboardService;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.service.model.FetchDashboardDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService service;
    private final ISessionUtil sessionUtil;

    @GetMapping("")
    public String getDefault(Model model) {

        return "dashboard/view-default";
    }

    @GetMapping("/content")
    public String getContent(Model model) {

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(sessionUtil.getCurrentUserEmail());
        FetchDashboardDetailsResponse response = service.fetchUserDashboard(query);

        model.addAttribute("details", response);

        return "dashboard/partial-content";
    }
}
