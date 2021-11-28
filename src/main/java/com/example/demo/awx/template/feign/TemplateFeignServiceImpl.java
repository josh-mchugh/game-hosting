package com.example.demo.awx.template.feign;

import com.example.demo.awx.template.feign.model.TemplateApi;
import com.example.demo.awx.template.feign.model.TemplateCreateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TemplateFeignServiceImpl implements TemplateFeignService {

    private final TemplateClient templateClient;

    @Override
    public TemplateApi createTemplate(TemplateCreateApi body) {

        return templateClient.createTemplate(body);
    }
}
