package com.example.demo.ovh.instance.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.project.entity.ProjectEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ovh_instance_group")
public class InstanceGroupEntity extends AbstractAggregateEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    public ProjectEntity projectEntity;

    @Column(name = "ovh_id", unique = true, nullable = false)
    private String ovhId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "instanceGroupEntity")
    private List<InstanceEntity> instanceEntities;
}
