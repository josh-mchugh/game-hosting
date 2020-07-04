package com.example.demo.email.mapper;

import com.example.demo.email.entity.EmailEntity;
import com.example.demo.email.model.Email;

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
                .context(entity.getContext())
                .mailingDate(entity.getMailingDate())
                .build();
    }
}
