package com.example.demo.ovh.instance.entity;

import com.example.demo.framework.database.AbstractEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import com.example.demo.ovh.credential.entity.CredentialEntity;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "instance")
public class InstanceEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flavor_id", nullable = false)
    private FlavorEntity flavorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "image_id", nullable = false)
    private ImageEntity imageEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instance_group_id", nullable = false)
    private InstanceGroupEntity instanceGroupEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "credential_id", nullable = false)
    private CredentialEntity credentialEntity;

    @NotNull
    @Column(name = "instance_id", unique = true, nullable = false)
    private String instanceId;

    @Column(name = "name")
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private InstanceStatus status;

    @Column(name = "instance_created_date")
    private LocalDateTime instanceCreatedDate;

    @Column(name = "ip4_address")
    private String ip4Address;

    @Column(name = "ip6_address")
    private String ip6Address;
}
