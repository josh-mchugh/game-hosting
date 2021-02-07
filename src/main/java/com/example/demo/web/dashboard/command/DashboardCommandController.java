package com.example.demo.web.dashboard.command;

import com.example.demo.framework.web.ModalResponse;
import com.example.demo.game.entity.GameType;
import com.example.demo.web.dashboard.command.service.IDashboardCommandService;
import com.example.demo.web.dashboard.command.model.DashboardProjectCreateForm;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateRequest;
import com.example.demo.web.dashboard.command.service.model.DashboardProjectCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardCommandController {

    private final IDashboardCommandService dashboardService;

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

        DashboardProjectCreateResponse response = dashboardService.handleProjectCreate(request);

        return new ModalResponse(model)
                .redirect(String.format("/project/%s", response.getProjectId()))
                .build();
    }
}
