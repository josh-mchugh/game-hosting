package com.example.demo.awx.template.feign;

import com.example.demo.awx.template.feign.model.TemplateApi;
import com.example.demo.awx.template.feign.model.TemplateCreateApi;
import com.example.demo.framework.feign.FeignAwxConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "templateClient", url = "${awx.base-url}", configuration = FeignAwxConfig.class)
public interface ITemplateClient {

    @PostMapping("/api/v2/job_templates/")
    TemplateApi createTemplate(@RequestBody TemplateCreateApi body);
}
