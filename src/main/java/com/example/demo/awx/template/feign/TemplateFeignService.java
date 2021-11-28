package com.example.demo.awx.template.feign;

import com.example.demo.awx.template.feign.model.TemplateApi;
import com.example.demo.awx.template.feign.model.TemplateCreateApi;

public interface TemplateFeignService {

    TemplateApi createTemplate(TemplateCreateApi body);
}
