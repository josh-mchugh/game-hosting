package com.example.demo.framework.seed.service;

import com.example.demo.awx.feign.playbook.PlaybookClient;
import com.example.demo.awx.playbook.model.AwxPlaybook;
import com.example.demo.awx.playbook.service.IAwxPlaybookService;
import com.example.demo.awx.playbook.service.model.AwxPlaybookCreateRequest;
import com.example.demo.awx.project.model.AwxProject;
import com.example.demo.awx.project.service.IAwxProjectService;
import com.example.demo.framework.properties.AwxConfig;
import com.example.demo.framework.seed.ISeedService;
import com.google.common.collect.ImmutableList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwxPlaybookSeedService implements ISeedService<AwxPlaybook> {

    private final AwxConfig awxConfig;
    private final IAwxProjectService awxProjectService;
    private final IAwxPlaybookService awxPlaybookService;
    private final PlaybookClient playbookClient;

    @Override
    public boolean dataNotExists() {

        return !awxPlaybookService.existsAny();
    }

    @Override
    public ImmutableList<AwxPlaybook> initializeData() {

        AwxProject awxProject = awxProjectService.getByName(awxConfig.getProject().getName());

        List<String> playbooks = playbookClient.getPlaybooks(awxProject.getProjectId());

        if (CollectionUtils.isEmpty(playbooks)) {
            try {

                Thread.sleep(10_000);
                playbooks = playbookClient.getPlaybooks(awxProject.getProjectId());

            } catch (Exception e) {

                log.info("Failed to retrieve playbooks from Awx API", e);
            }
        }

        return playbooks.stream()
                .map(playbook -> buildPlaybookCreateRequest(playbook, awxProject.getProjectId()))
                .map(awxPlaybookService::handleCreateRequest)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public String type() {

        return "Awx Playbook";
    }

    @Override
    public Integer order() {

        return 10;
    }

    private AwxPlaybookCreateRequest buildPlaybookCreateRequest(String playbook, Long projectId) {

        return AwxPlaybookCreateRequest.builder()
                .projectId(projectId)
                .name(playbook)
                .build();
    }
}
