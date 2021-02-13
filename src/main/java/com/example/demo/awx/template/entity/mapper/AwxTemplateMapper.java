package com.example.demo.awx.template.entity.mapper;

import com.example.demo.awx.template.entity.AwxTemplateEntity;
import com.example.demo.awx.template.entity.model.AwxTemplate;

public class AwxTemplateMapper {

    public static AwxTemplate map(AwxTemplateEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxTemplate.builder()
                .id(entity.getUUID())
                .awxId(entity.getAwxId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .verbosity(entity.getVerbosity())
                .build();
    }
}
