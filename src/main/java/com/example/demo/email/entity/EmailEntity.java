package com.example.demo.email.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.framework.database.converter.CollectionConverter;
import com.example.demo.framework.database.converter.MapConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "email")
public class EmailEntity extends AbstractAggregateEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "template", nullable = false)
    private EmailTemplateType template;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EmailStatus status;

    @Column(name = "to_address", nullable = false)
    private String toAddress;

    @Lob
    @Convert(converter = MapConverter.class)
    @Column(name = "body_context")
    private Map<String, Object> bodyContext;

    @Lob
    @Convert(converter = CollectionConverter.class)
    @Column(name = "subject_context")
    private List<Object> subjectContext;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;
}
