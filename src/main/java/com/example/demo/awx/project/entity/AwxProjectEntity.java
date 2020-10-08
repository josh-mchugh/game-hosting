package com.example.demo.awx.project.entity;

import com.example.demo.awx.credential.entity.AwxCredentialEntity;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.framework.database.AbstractAggregateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "awx_project")
public class AwxProjectEntity extends AbstractAggregateEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_organization_id", nullable = false)
    private AwxOrganizationEntity awxOrganizationEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_credential_id", nullable = false)
    private AwxCredentialEntity awxCredentialEntity;

    @Column(name = "project_id", unique = true, nullable = false)
    private Long projectId;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "scm_type", nullable = false)
    private String scmType;

    @Column(name = "scm_url", nullable = false)
    private String scmUrl;

    @Column(name = "scm_branch", nullable = false)
    private String scmBranch;
}
