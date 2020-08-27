package com.example.demo.awx.credential.entity;

import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
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
@Table(name = "awx_credential")
public class AwxCredentialEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_organization_id", nullable = false)
    private AwxOrganizationEntity awxOrganizationEntity;

    @Column(name = "credential_id", unique = true, nullable = false)
    private Long credentialId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "private_key", nullable = false)
    private String privateKey;

    @Column(name = "passphrase")
    private String passphrase;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AwxCredentialType type;
}
