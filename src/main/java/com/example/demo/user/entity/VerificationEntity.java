package com.example.demo.user.entity;

import com.example.demo.framework.database.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_verification")
@EqualsAndHashCode(callSuper = true)
public class VerificationEntity extends AbstractEntity {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private VerificationStatus status;

    @Column(name = "token")
    private String token;

    @Column(name = "sent_date")
    private LocalDateTime sentDate;

    @Column(name = "verification_date")
    private LocalDateTime verificationDate;
}