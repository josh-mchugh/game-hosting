package com.example.demo.awx.playbook.entity.service;

import com.example.demo.awx.playbook.entity.model.AwxPlaybook;
import com.example.demo.awx.playbook.aggregate.event.AwxPlaybookCreatedEvent;

public interface AwxPlaybookService {

    AwxPlaybook handleAwxPlaybookCreated(AwxPlaybookCreatedEvent event);
}
