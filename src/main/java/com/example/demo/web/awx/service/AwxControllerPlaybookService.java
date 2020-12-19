package com.example.demo.web.awx.service;

import com.example.demo.awx.playbook.aggregate.command.AwxPlaybookCreateCommand;
import com.example.demo.awx.playbook.feign.IPlaybookFeignService;
import com.example.demo.awx.playbook.projection.IAwxPlaybookProjector;
import com.example.demo.awx.project.entity.model.AwxProject;
import com.example.demo.awx.project.projection.IAwxProjectProjector;
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

    private final IAwxProjectProjector awxProjectProjector;
    private final IAwxPlaybookProjector awxPlaybookProjector;
    private final IPlaybookFeignService playbookFeignService;
    private final CommandGateway commandGateway;

    @Override
    public ImmutableList<Object> handleCreatePlaybooks(PlaybookCreateRequest request) {

        // TODO: Refactor into Create / Update functionality
        if(awxPlaybookProjector.existsAny()) {

            log.info("Playbooks already exist....");

            return ImmutableList.of();
        }

        log.info("Retrieving Playbooks from AWX...");

        AwxProject awxProject = awxProjectProjector.getByProjectId(request.getProjectId());

        List<Object> awxPlaybooks = playbookFeignService.getPlaybooks(awxProject.getAwxId()).stream()
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
