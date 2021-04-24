package com.example.demo.web.project.dashboard;

import com.example.demo.web.project.dashboard.command.IProjectCommandService;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStartRequest;
import com.example.demo.web.project.dashboard.command.model.ProjectInstanceStopRequest;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsQuery;
import com.example.demo.web.project.dashboard.projection.model.FetchProjectDetailsResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/project/dashboard")
@RequiredArgsConstructor
public class ProjectDashboardController {

    private final IProjectCommandService commandService;
    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public String getDefault(Model model, @PathVariable("id") String id) throws ExecutionException, InterruptedException {

        FetchProjectDetailsQuery query = new FetchProjectDetailsQuery(id);
        FetchProjectDetailsResponse response = queryGateway.query(query, FetchProjectDetailsResponse.class).get();

        model.addAttribute("details", response);

        return "project/view-default";
    }

    @ResponseBody
    @PostMapping("/{id}/instance/{instanceId}/start")
    public ResponseEntity<?> postStartInstance(@PathVariable("id") String id, @PathVariable("instanceId") String instanceId) throws ExecutionException, InterruptedException {

        ProjectInstanceStartRequest request = ProjectInstanceStartRequest.builder()
                .projectId(id)
                .instanceId(instanceId)
                .build();

        commandService.handleProjectInstanceStart(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/{id}/instance/{instanceId}/stop")
    public ResponseEntity<?> postStopInstance(@PathVariable("id") String id, @PathVariable("instanceId") String instanceId) throws ExecutionException, InterruptedException {

        ProjectInstanceStopRequest request = ProjectInstanceStopRequest.builder()
                .projectId(id)
                .instanceId(instanceId)
                .build();

        commandService.handleProjectInstanceStop(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
