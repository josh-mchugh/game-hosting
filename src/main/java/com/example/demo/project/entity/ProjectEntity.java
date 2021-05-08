package com.example.demo.project.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.game.entity.GameEntity;
import com.example.demo.ovh.instance.entity.InstanceGroupEntity;
import com.example.demo.ovh.region.entity.RegionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "project")
public class ProjectEntity extends AbstractAggregateEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private GameEntity gameEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ovh_region_id")
    private RegionEntity regionEntity;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectEntity")
    private InstanceGroupEntity instanceGroupEntity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projectEntity")
    private List<ProjectMembershipEntity> projectMembershipsEntities;
}
