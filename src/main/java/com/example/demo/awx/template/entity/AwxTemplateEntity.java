package com.example.demo.awx.template.entity;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.awx.playbook.entity.AwxPlayBookEntity;
import com.example.demo.framework.database.AbstractAggregateEntity;
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
public class AwxTemplateEntity extends AbstractAggregateEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_credential_id", nullable = false)
    private AwxCredentialEntity awxCredentialEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_inventory_id", nullable = false)
    private AwxInventoryEntity awxInventoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_playbook_id", nullable = false)
    private AwxPlayBookEntity awxPlayBookEntity;

    @Column(name = "awx_id", unique = true, nullable = false)
    private Long awxId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TemplateJobType type;

    @Column(name = "verbosity", nullable = false)
    @Enumerated(EnumType.STRING)
    private TemplateVerbosity verbosity;
}
