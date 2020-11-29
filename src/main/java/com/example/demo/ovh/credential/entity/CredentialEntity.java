package com.example.demo.ovh.credential.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "credential")
public class CredentialEntity extends AbstractAggregateEntity {

    @Column(name = "ssh_key_id", unique = true, nullable = false)
    private String sshKeyId;

    @Column(name = "name")
    private String name;

    @Column(name = "public_key")
    private String publicKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CredentialType type;
}
