package com.example.demo.web.awx.service;

import com.example.demo.awx.feign.playbook.PlaybookClient;
import com.example.demo.awx.playbook.model.AwxPlaybook;
import com.example.demo.awx.playbook.service.IAwxPlaybookService;
import com.example.demo.awx.playbook.service.model.AwxPlaybookCreateRequest;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.IAwxProjectService;
import com.example.demo.web.awx.service.model.PlaybookCreateRequest;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwxControllerPlaybookService implements IAwxControllerPlaybookService {

    private final IAwxProjectService awxProjectService;
    private final IAwxPlaybookService awxPlaybookService;
    private final PlaybookClient playbookClient;

    @Override
    public ImmutableList<AwxPlaybook> handleCreatePlaybooks(PlaybookCreateRequest request) {

        // TODO: Refactor into Create / Update functionality
        if(awxPlaybookService.existsAny()) {

            log.info("Playbooks already exist....");

            return ImmutableList.of();
        }

        log.info("Retrieving Playbooks from AWX...");

        AwxProject awxProject = awxProjectService.getByProjectId(request.getProjectId());

        List<AwxPlaybook> awxPlaybooks = playbookClient.getPlaybooks(awxProject.getProjectId()).stream()
                .map(playbook -> buildPlaybookCreateRequest(playbook, awxProject.getProjectId()))
                .map(awxPlaybookService::handleCreateRequest)
                .collect(Collectors.toList());

        log.info("Persisted {} AwxPlaybooks", awxPlaybooks.size());

        return ImmutableList.copyOf(awxPlaybooks);
    }

    private AwxPlaybookCreateRequest buildPlaybookCreateRequest(String playbook, Long projectId) {

        return AwxPlaybookCreateRequest.builder()
                .projectId(projectId)
                .name(playbook)
                .build();
    }
}
