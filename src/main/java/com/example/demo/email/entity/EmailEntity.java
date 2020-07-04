package com.example.demo.email.entity;

import com.example.demo.framework.database.AbstractEntity;
import com.example.demo.framework.database.converter.MapConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Entity
@Table(name = "email")
@EqualsAndHashCode(callSuper = true)
public class EmailEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "template")
    private EmailTemplate template;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EmailStatus status;

    @Column(name = "to_address")
    private String toAddress;

    @Convert(converter = MapConverter.class)
    @Column(name = "context")
    private Map<String, Object> context;

    @Column(name = "mailing_date")
    private LocalDateTime mailingDate;
}
