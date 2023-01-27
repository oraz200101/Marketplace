package com.example.marketplace.model.entity;

import com.example.marketplace.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Objects;

import static com.example.marketplace.constants.swagger.CategorySwaggerConstants.NAME_OF_CATEGORY_DESCRIPTION;
import static com.example.marketplace.constants.swagger.CategorySwaggerConstants.NAME_OF_CATEGORY_EXAMPLE;

@Entity
@Data
public class Category extends BaseEntity {
    @NotBlank
    @Schema(description = NAME_OF_CATEGORY_DESCRIPTION,example = NAME_OF_CATEGORY_EXAMPLE)
    private String name;
    @ManyToMany(mappedBy = "categories")
    List<Product> products;


}
