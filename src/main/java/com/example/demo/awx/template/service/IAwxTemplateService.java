package com.example.demo.awx.template.service;

import com.example.demo.awx.template.model.AwxTemplate;
import com.example.demo.awx.template.service.model.AwxTemplateCreateRequest;

public interface IAwxTemplateService {

    boolean existsAny();

    AwxTemplate handleCreateRequest(AwxTemplateCreateRequest request);
}
