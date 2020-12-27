package com.example.demo.web.dashboard.projection;

import com.example.demo.web.dashboard.command.model.DashboardProjectCreateForm;
import com.example.demo.web.dashboard.projection.service.IDashboardProjectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardProjectionController {

    private final IDashboardProjectionService dashboardService;

    @GetMapping("")
    public String getDefault(Model model) {

        return "dashboard/view-default";
    }

    @GetMapping("/content")
    public String getContent(Model model) {

        model.addAttribute("details", dashboardService.getDashboardDetails());

        return "dashboard/partial-content";
    }

    @GetMapping("/project/create")
    public String getProjectCreateModal(@ModelAttribute("form") DashboardProjectCreateForm form) {

        return "dashboard/modal-project-create";
    }
}
