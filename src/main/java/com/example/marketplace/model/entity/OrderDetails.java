package com.example.marketplace.model.entity;

import com.example.marketplace.model.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private BigDecimal amount;
    private BigDecimal price;
    public OrderDetails(Order order, Product product, Long amount) {
        this.order = order;
        this.product = product;
        this.amount = new BigDecimal(amount);
        this.price = new BigDecimal(product.getPrice());
    }
}

