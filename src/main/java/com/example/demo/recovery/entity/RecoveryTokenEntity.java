package com.example.demo.recovery.entity;

import com.example.demo.framework.database.AbstractEntity;
import com.example.demo.user.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_recovery_token")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RecoveryTokenEntity extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "sent_date", nullable = false)
    private LocalDateTime sentDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    public RecoveryTokenEntity(UserEntity userEntity) {

        this.userEntity = userEntity;
    }
}
