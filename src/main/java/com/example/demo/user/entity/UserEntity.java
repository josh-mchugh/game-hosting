package com.example.demo.user.entity;

import com.example.demo.framework.database.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private UserState state;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;

    @Column(name = "invalid_login_attempts")
    private Long invalidLoginAttempts;

    @Column(name = "last_login_date")
    private LocalDateTime lastLoginDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userEntity")
    private RecoveryTokenEntity recoveryTokenEntity;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userEntity")
    private VerificationEntity verificationEntity;
}
