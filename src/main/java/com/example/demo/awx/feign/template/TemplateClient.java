package com.example.demo.awx.feign.template;

import com.example.demo.awx.feign.template.model.TemplateApi;
import com.example.demo.awx.feign.template.model.TemplateCreateApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TemplateClient {

    @PostMapping("/api/v2/job_templates/")
    TemplateApi createTemplate(@RequestBody TemplateCreateApi body);
}
