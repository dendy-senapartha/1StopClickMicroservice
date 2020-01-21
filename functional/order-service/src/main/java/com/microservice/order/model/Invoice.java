package com.microservice.order.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

/*
 * Created by dendy-prtha on 15/05/2019.
 * invoice Model
 * https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/
 */

@Entity
@Table(name = "invoice")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @OneToOne
    @MapsId
    @Getter
    @Setter
    private Orders orders;

    @Column(name = "user_id")
    @Getter
    @Setter
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "payment_method_id")
    @Setter
    private PaymentMethod paymentMethod;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "status")
    @Getter
    @Setter
    private String status;

    @Column(name = "created")
    @Getter
    @Setter
    private Date created;

    public PaymentMethod getPaymentMethod() {
        return (PaymentMethod) Hibernate.unproxy(paymentMethod) ;
    }
}
