package com.example.marketplace.model.entity;

import com.example.marketplace.model.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static com.example.marketplace.constants.swagger.ProductSwaggerConstants.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class Product extends BaseEntity {
    @Schema(description = PRODUCT_NAME_DESCRIPTION,example = PRODUCT_NAME_EXAMPLE)
    private String name;
    @Schema(description = PRODUCT_DESCRIPTION_DESCRIPTION,example = PRODUCT_DESCRIPTION_EXAMPLE)
    private String description;
    @Schema(description = PRODUCT_PRICE_DESCRIPTION,example = PRODUCT_PRICE_EXAMPLE)
    private Double price;
    @Schema(description = PRODUCT_RAITING_DESCRIPTION,example = PRODUCT_RAITING_EXAMPLE)
    private Double raiting;
    @JsonIgnore
    @ManyToMany(
            cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category>categories;
    @JsonIgnore
    @ManyToMany(mappedBy = "products",targetEntity = Bucket.class)
    private List<Bucket> buckets;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments;
}
