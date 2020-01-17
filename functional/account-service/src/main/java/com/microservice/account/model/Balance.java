package com.microservice.account.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/*
 * Created by dendy-prtha on 17/01/2020.
 * Balance Model
 */

@Entity
@Table(name = "balance")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @Column(name = "user_id")
    @Getter
    @Setter
    private Long userId;

    @Column(name = "balance")
    @Getter
    @Setter
    private BigDecimal balance;

    @Column(name = "last_usage")
    @Getter
    @Setter
    private Date lastUsage;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "balance_type_id")
    @Setter
    private BalanceType balanceType;

    public BalanceType getBalanceType() {
        return (BalanceType) Hibernate.unproxy(balanceType);
    }
}

