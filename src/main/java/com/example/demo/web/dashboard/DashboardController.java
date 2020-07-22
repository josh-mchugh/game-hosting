package com.example.demo.web.dashboard;

import com.example.demo.web.dashboard.service.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final IDashboardService dashboardService;

    @GetMapping("")
    public String getDefault(Model model) {

        return "dashboard/view-default";
    }

    @GetMapping("/content")
    public String getContent(Model model) {

        model.addAttribute("details", dashboardService.getDashboardDetails());

        return "dashboard/partial-content";
    }
}
