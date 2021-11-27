package com.example.demo.web.awx;

import com.example.demo.awx.playbook.aggregate.command.AwxPlaybookCreateCommand;
import com.example.demo.awx.playbook.feign.IPlaybookFeignService;
import com.example.demo.web.awx.service.AwxService;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksQuery;
import com.example.demo.web.awx.service.model.ExistsAnyPlaybooksResponse;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdQuery;
import com.example.demo.web.awx.service.model.FetchProjectByAwxIdResponse;
import com.example.demo.web.awx.service.projection.ProjectProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/awx")
@RequiredArgsConstructor
public class AwxController {

    private final AwxService service;
    private final CommandGateway commandGateway;
    private final IPlaybookFeignService playbookFeignService;

    @PostMapping("/notification/project/{projectId}/success")
    public ResponseEntity<Void> notificationProjectCallback(@PathVariable("projectId") Long projectId) throws ExecutionException, InterruptedException {

        // TODO: Refactor into Create / Update functionality
        if(existsAnyPlaybooks()) {

            log.info("Playbooks already exist....");

            return new ResponseEntity<>(HttpStatus.OK);
        }

        log.info("Retrieving Playbooks from AWX...");

        ProjectProjection project = getProject(projectId);

        List<Object> awxPlaybooks = playbookFeignService.getPlaybooks(project.getAwxId()).stream()
                .map(playbook -> buildAwxPlaybookCreateCommand(playbook, project.getId()))
                .map(commandGateway::sendAndWait)
                .collect(Collectors.toList());

        log.info("Persisted {} AwxPlaybooks", awxPlaybooks.size());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean existsAnyPlaybooks() {

        ExistsAnyPlaybooksQuery query = new ExistsAnyPlaybooksQuery();
        ExistsAnyPlaybooksResponse response = service.existsAnyPlaybooks(query);

        return response.exists();
    }

    private ProjectProjection getProject(Long awxId) {

        FetchProjectByAwxIdQuery query = new FetchProjectByAwxIdQuery(awxId);
        FetchProjectByAwxIdResponse response = service.getProjectByAwxId(query);

        return response.getProject();
    }

    private AwxPlaybookCreateCommand buildAwxPlaybookCreateCommand(String name, UUID awxProjectId) {

        return AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProjectId)
                .name(name)
                .build();
    }

}
