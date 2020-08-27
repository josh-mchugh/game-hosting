package com.example.demo.awx.playbook.entity;

import com.example.demo.awx.project.entity.AwxProjectEntity;
import com.example.demo.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "awx_playbook")
public class AwxPlayBookEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_project_id", nullable = false)
    private AwxProjectEntity awxProjectEntity;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private PlaybookType type;
}
