package com.example.demo.ovh.image.entity;

import com.example.demo.framework.database.AbstractEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "image")
public class ImageEntity extends AbstractEntity {

    @Column(name = "image_id", nullable=false)
    private String imageId;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private RegionEntity regionEntity;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "image_created_date")
    private LocalDateTime imageCreatedDate;

    @Column(name = "flavor_type")
    private String flavorType;

    @Column(name = "hourly")
    private String hourly;

    @Column(name = "monthly")
    private String monthly;

    @Column(name = "size")
    private Double size;

    @Column(name = "min_ram")
    private Integer minRam;

    @Column(name = "min_disk")
    private Integer minDisk;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private String status;

    @Column(name = "visibility")
    private String visibility;
}
