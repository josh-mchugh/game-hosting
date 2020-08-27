package com.example.demo.awx.template.mapper;

import com.example.demo.awx.template.entity.AwxTemplateEntity;
import com.example.demo.awx.template.model.AwxTemplate;

public class AwxTemplateMapper {

    public static AwxTemplate map(AwxTemplateEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxTemplate.builder()
                .id(entity.getId())
                .templateId(entity.getTemplateId())
                .name(entity.getName())
                .description(entity.getDescription())
                .jobType(entity.getJobType())
                .verbosity(entity.getVerbosity())
                .build();
    }
}
