package com.example.demo.email.entity.mapper;

import com.example.demo.email.entity.EmailEntity;
import com.example.demo.email.entity.model.Email;

public class EmailMapper {

    public static Email map(EmailEntity entity) {

        if(entity == null) {

            return null;
        }

        return Email.builder()
                .id(entity.getId())
                .createdDate(entity.getCreatedDate())
                .template(entity.getTemplate())
                .status(entity.getStatus())
                .toAddress(entity.getToAddress())
                .bodyContext(entity.getBodyContext())
                .subjectContext(entity.getSubjectContext())
                .sentDate(entity.getSentDate())
                .build();
    }
}
