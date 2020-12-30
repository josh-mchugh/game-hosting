package com.example.demo.web.project.command;

import com.example.demo.web.project.command.service.IProjectCommandService;
import com.example.demo.web.project.command.service.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.command.service.model.ProjectInstanceStopRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectCommandController {

    private final IProjectCommandService projectCommandService;

    @ResponseBody
    @PostMapping("/{id}/instance/{instanceId}/start")
    public ResponseEntity<?> postStartInstance(@PathVariable("id") String id, @PathVariable("instanceId") String instanceId) {

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId(id)
                .instanceId(instanceId)
                .build();

        projectCommandService.handleProjectInstanceStart(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/{id}/instance/{instanceId}/stop")
    public ResponseEntity<?> postStopInstance(@PathVariable("id") String id, @PathVariable("instanceId") String instanceId) {

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId(id)
                .instanceId(instanceId)
                .build();

        projectCommandService.handleProjectInstanceStop(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
