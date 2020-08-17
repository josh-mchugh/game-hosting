package com.example.demo.ovh.flavor.entity;

import com.example.demo.framework.database.AbstractEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "flavor")
public class FlavorEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity regionEntity;

    @Column(name = "flavor_id", unique = true, nullable = false)
    private String flavorId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "available")
    private Boolean available;

    @Column(name = "hourly")
    private String hourly;

    @Column(name = "monthly")
    private String monthly;

    @Column(name = "quota")
    private Integer quota;

    @Column(name = "os_type")
    private String osType;

    @Column(name = "vcpus")
    private Integer vcpus;

    @Column(name = "ram")
    private Integer ram;

    @Column(name = "disk")
    private Integer disk;

    @Column(name = "inbound_bandwidth")
    private Integer inboundBandwidth;

    @Column(name = "outbound_bandwidth")
    private Integer outboundBandwidth;
}
