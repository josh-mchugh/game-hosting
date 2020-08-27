package com.example.demo.awx.playbook.service;

import com.example.demo.awx.playbook.model.AwxPlaybook;
import com.example.demo.awx.playbook.service.model.AwxPlaybookCreateRequest;

public interface IAwxPlaybookService {

    boolean existsAny();

    AwxPlaybook handleCreateRequest(AwxPlaybookCreateRequest request);
}
