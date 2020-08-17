package com.example.demo.web.project;

import com.example.demo.web.project.service.IProjectControllerService;
import com.example.demo.web.project.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.service.model.ProjectInstanceStopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final IProjectControllerService projectControllerService;

    @GetMapping("/{id}")
    public String getDefault(Model model, @PathVariable("id") String id) {

        model.addAttribute("details", projectControllerService.getProjectDetails(id));

        return "project/view-default";
    }

    @ResponseBody
    @PostMapping("/{id}/instance/{instanceId}/start")
    public ResponseEntity<?> postStartInstance(@PathVariable("id") String id, @PathVariable("instanceId") String instanceId) {

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId(id)
                .instanceId(instanceId)
                .build();

        projectControllerService.handleProjectInstanceStart(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/{id}/instance/{instanceId}/stop")
    public ResponseEntity<?> postStopInstance(@PathVariable("id") String id, @PathVariable("instanceId") String instanceId) {

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId(id)
                .instanceId(instanceId)
                .build();

        projectControllerService.handleProjectInstanceStop(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
