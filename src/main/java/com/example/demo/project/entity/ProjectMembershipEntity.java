package com.example.demo.project.entity;

import com.example.demo.framework.database.AbstractAggregateEntity;
import com.example.demo.user.entity.UserEntity;
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

@Getter
@Setter
@Entity
@Table(name = "project_membership")
public class ProjectMembershipEntity extends AbstractAggregateEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity projectEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private ProjectMembershipRole role;
}
