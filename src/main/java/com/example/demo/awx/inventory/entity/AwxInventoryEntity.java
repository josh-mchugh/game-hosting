package com.example.demo.awx.inventory.entity;

import com.example.demo.awx.host.entity.AwxHostEntity;
import com.example.demo.awx.organization.entity.AwxOrganizationEntity;
import com.example.demo.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "awx_inventory")
public class AwxInventoryEntity extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awx_organization_id", nullable = false)
    private AwxOrganizationEntity awxOrganizationEntity;

    @Column(name = "inventory_id", unique = true, nullable = false)
    private Long inventoryId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "awxInventoryEntity")
    private List<AwxHostEntity> awxHostEntities;
}
