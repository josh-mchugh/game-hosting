package com.example.demo.awx.organization.entity;

import com.example.demo.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "awx_organization")
public class AwxOrganizationEntity extends AbstractEntity {

    @Column(name = "organization_id", unique = true, nullable = false)
    private Long organizationId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
