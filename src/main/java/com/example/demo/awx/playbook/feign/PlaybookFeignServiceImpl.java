package com.example.demo.awx.playbook.feign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlaybookFeignServiceImpl implements PlaybookFeignService {

    private final PlaybookClient playbookClient;

    @Override
    public List<String> getPlaybooks(Long projectId) {

        return playbookClient.getPlaybooks(projectId);
    }
}
