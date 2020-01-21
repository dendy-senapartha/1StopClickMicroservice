package com.microservice.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;

/*
 * Created by dendy-prtha on 15/05/2019.
 * order_item Model
 */

@Entity
@Table(name = "order_item")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("orderItemList")
    @Setter
    private Orders order;

    @Column(name = "product_id")
    @Getter
    @Setter
    private int productId;

    @Column(name = "quantity")
    @Getter
    @Setter
    private int quantity;

    @Column(name = "subtotal")
    @Getter
    @Setter
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_category_id")
    @Getter
    @Setter
    private OrderItemCategory category;

    public Orders getOrder() {
        return (Orders) Hibernate.unproxy(order);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderItem)) return false;
        return id == ((OrderItem) object).getId();
    }
}
