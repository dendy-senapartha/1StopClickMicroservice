package com.microservice.order.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnoreProperties("orderItemList")
    private Orders order;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_category_id")
    private OrderItemCategory category;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrder() {
        return (Orders) Hibernate.unproxy(order);
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OrderItem)) return false;
        return id == ((OrderItem) object).getId();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public OrderItemCategory getCategory() {
        return category;
    }

    public void setCategory(OrderItemCategory category) {
        this.category = category;
    }
}
