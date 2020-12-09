package com.example.demo.awx.host.entity;

import com.example.demo.awx.inventory.entity.AwxInventoryEntity;
import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.ovh.instance.entity.InstanceEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "awx_host")
public class AwxHostEntity extends AbstractAggregateEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_inventory_id", nullable = false)
    private AwxInventoryEntity awxInventoryEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instance_id", nullable = false)
    private InstanceEntity instanceEntity;

    @Column(name = "awx_id", unique = true, nullable = false)
    private Long awxId;

    @Column(name = "hostname", unique = true, nullable = false)
    private String hostname;

    @Column(name = "description")
    private String description;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}
