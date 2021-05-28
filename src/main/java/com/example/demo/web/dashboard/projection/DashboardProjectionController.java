package com.example.demo.web.dashboard.projection;

import com.example.demo.framework.security.session.ISessionUtil;
import com.example.demo.web.dashboard.projection.service.model.FetchDashboardDetailsQuery;
import com.example.demo.web.dashboard.projection.service.model.FetchDashboardDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardProjectionController {

    private final QueryGateway queryGateway;
    private final ISessionUtil sessionUtil;

    @GetMapping("")
    public String getDefault(Model model) {

        return "dashboard/view-default";
    }

    @GetMapping("/content")
    public String getContent(Model model) throws ExecutionException, InterruptedException {

        FetchDashboardDetailsQuery query = new FetchDashboardDetailsQuery(sessionUtil.getCurrentUserEmail());
        FetchDashboardDetailsResponse response = queryGateway.query(query, FetchDashboardDetailsResponse.class).get();

        model.addAttribute("details", response);

        return "dashboard/partial-content";
    }
}
