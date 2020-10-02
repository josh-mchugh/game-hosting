package com.example.demo.awx.playbook.entity.mapper;

import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.awx.playbook.entity.model.AwxPlaybook;

public class AwxPlaybookMapper {

    public static AwxPlaybook map(AwxPlayBookEntity entity) {

        if (entity == null) {

            return null;
        }

        return AwxPlaybook.builder()
                .id(entity.getId())
                .name(entity.getName())
                .type(entity.getType())
                .build();
    }
}
