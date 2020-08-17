package com.example.demo.web.dashboard;

import com.example.demo.framework.component.ModalResponse;
import com.example.demo.game.entity.GameType;
import com.example.demo.web.dashboard.model.DashboardProjectCreateForm;
import com.example.demo.web.dashboard.service.IDashboardService;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.service.model.DashboardProjectCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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

    @GetMapping("/project/create")
    public String getProjectCreateModal(@ModelAttribute("form") DashboardProjectCreateForm form) {

        return "dashboard/modal-project-create";
    }

    @PostMapping("/project/create")
    public String postProjectCreateModal(Model model, @Valid @ModelAttribute("form") DashboardProjectCreateForm form, BindingResult results) {

        if(results.hasErrors()) {

            return "dashboard/modal-project-create";
        }

        DashboardProjectCreateRequest request = DashboardProjectCreateRequest.builder()
                .name(form.getName())
                .region("US-EAST-VA-1")
                .flavor("a64381e7-c4e7-4b01-9fbe-da405c544d2e")
                .gameType(GameType.MINECRAFT_JAVA)
                .build();

         DashboardProjectCreateResponse response = dashboardService.handleDashboardProjectCreate(request);

        return new ModalResponse(model)
                .redirect(String.format("/project/%s", response.getProjectId()))
                .build();
    }
}
