package com.example.demo.awx.template.entity.service;

import com.example.demo.awx.template.aggregate.event.AwxTemplateCreatedEvent;
import com.example.demo.awx.template.entity.model.AwxTemplate;

public interface AwxTemplateService {

    AwxTemplate handleAwxTemplateCreated(AwxTemplateCreatedEvent event);
}
