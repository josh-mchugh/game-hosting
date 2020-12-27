package com.example.demo.web.awx;

import com.example.demo.web.awx.service.IAwxControllerPlaybookService;
import com.example.demo.web.awx.service.model.PlaybookCreateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/awx")
@RequiredArgsConstructor
public class AwxCommandController {

    private final IAwxControllerPlaybookService awxControllerPlaybookService;

    @PostMapping("/notification/project/{projectId}/success")
    public ResponseEntity<Void> notificationProjectCallback(@PathVariable("projectId") Long projectId) {

        PlaybookCreateRequest request = new PlaybookCreateRequest(projectId);
        awxControllerPlaybookService.handleCreatePlaybooks(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
