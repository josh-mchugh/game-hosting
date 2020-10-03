package com.example.demo.web.awx.service;

import com.example.demo.awx.feign.playbook.PlaybookClient;
import com.example.demo.awx.playbook.aggregate.command.AwxPlaybookCreateCommand;
import com.example.demo.awx.playbook.projector.IAwxPlaybookProjector;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.IAwxProjectService;
import com.example.demo.web.awx.service.model.PlaybookCreateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwxControllerPlaybookService implements IAwxControllerPlaybookService {

    private final IAwxProjectService awxProjectService;
    private final IAwxPlaybookProjector awxPlaybookProjector;
    private final PlaybookClient playbookClient;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<Object> handleCreatePlaybooks(PlaybookCreateRequest request) {

        // TODO: Refactor into Create / Update functionality
        if(awxPlaybookProjector.existsAny()) {

            log.info("Playbooks already exist....");

            return ImmutableList.of();
        }

        log.info("Retrieving Playbooks from AWX...");

        AwxProject awxProject = awxProjectService.getByProjectId(request.getProjectId());

        List<Object> awxPlaybooks = playbookClient.getPlaybooks(awxProject.getProjectId()).stream()
                .map(playbook ->buildAwxPlaybookCreateCommand(playbook, awxProject.getId()))
                .map(commandGateway::sendAndWait)
                .collect(Collectors.toList());

        log.info("Persisted {} AwxPlaybooks", awxPlaybooks.size());

        return ImmutableList.copyOf(awxPlaybooks);
    }

    private AwxPlaybookCreateCommand buildAwxPlaybookCreateCommand(String name, String awxProjectId) {

        return AwxPlaybookCreateCommand.builder()
                .id(UUID.randomUUID())
                .awxProjectId(awxProjectId)
                .name(name)
                .build();
    }
}
