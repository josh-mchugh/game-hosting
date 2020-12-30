package com.example.demo.web.project.projection;

import com.example.demo.web.project.projection.service.IProjectProjectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectProjectorController {

    private final IProjectProjectorService projectControllerService;

    @GetMapping("/{id}")
    public String getDefault(Model model, @PathVariable("id") String id) {

        model.addAttribute("details", projectControllerService.getProjectDetails(id));

        return "project/view-default";
    }
}
