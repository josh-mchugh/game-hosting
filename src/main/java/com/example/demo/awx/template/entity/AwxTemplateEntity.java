package com.example.demo.awx.template.entity;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
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
@Table(name = "awx_template")
public class AwxTemplateEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_credential_id", nullable = false)
    private AwxCredentialEntity awxCredentialEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_inventory_id", nullable = false)
    private AwxInventoryEntity awxInventoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_playbook_id", nullable = false)
    private AwxPlayBookEntity awxPlayBookEntity;

    @Column(name = "template_id", unique = true, nullable = false)
    private Long templateId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "job_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TemplateJobType jobType;

    @Column(name = "verbosity", nullable = false)
    @Enumerated(EnumType.STRING)
    private TemplateVerbosity verbosity;
}
