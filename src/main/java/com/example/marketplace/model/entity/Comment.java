package com.example.marketplace.model.entity;

import com.example.marketplace.model.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
   private String description;
   @DecimalMin("0.0")
   @DecimalMax("5.0")
   private double raiting;
   @ManyToOne
   @JoinColumn(name = "product_id")
   @JsonIgnore
   private Product product;


}
