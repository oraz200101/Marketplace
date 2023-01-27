package com.example.marketplace.model.entity;

import com.example.marketplace.model.entity.base.BaseEntity;
import com.example.marketplace.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetails;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal sum;
    private String address;
}
