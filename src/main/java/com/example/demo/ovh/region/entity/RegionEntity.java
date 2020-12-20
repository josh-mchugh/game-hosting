package com.example.demo.ovh.region.entity;

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
@Table(name = "ovh_region")
public class RegionEntity extends AbstractAggregateEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "continent_code")
    private String continentCode;

    @Column(name = "country_codes")
    private String countryCodes;

    @Column(name = "data_center_location")
    private String dataCenterLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RegionStatus status;
}
