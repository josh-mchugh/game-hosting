package com.example.demo.ovh.instance.entity;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.ovh.credential.entity.CredentialEntity;
import com.example.demo.ovh.flavor.entity.FlavorEntity;
import com.example.demo.ovh.image.entity.ImageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "ovh_instance")
public class InstanceEntity extends AbstractAggregateEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ovh_flavor_id", nullable = false)
    private FlavorEntity flavorEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ovh_image_id", nullable = false)
    private ImageEntity imageEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ovh_instance_group_id", nullable = false)
    private InstanceGroupEntity instanceGroupEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ovh_credential_id", nullable = false)
    private CredentialEntity credentialEntity;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "instanceEntity")
    private AwxHostEntity awxHostEntity;

    @Column(name = "ovh_id", unique = true, nullable = false)
    private String ovhId;

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
