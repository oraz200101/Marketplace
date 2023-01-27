package com.example.marketplace.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import static com.example.marketplace.constants.swagger.BaseEntitySwaggerConstants.FIELD_ID_DESCRIPTION;
import static com.example.marketplace.constants.swagger.BaseEntitySwaggerConstants.FIELD_ID_EXAMPLE;
import static com.example.marketplace.constants.swagger.ProductSwaggerConstants.*;

@Data
@Schema(title = PRODUCT_UPDATE_DTO_TITLE)
public class ProductUpdateDto {
    @Schema(description = FIELD_ID_DESCRIPTION,example = FIELD_ID_EXAMPLE)
    private Long id;

    @Schema(description = PRODUCT_NAME_DESCRIPTION,example = PRODUCT_NAME_EXAMPLE)
    private String name;

    @Schema(description = PRODUCT_DESCRIPTION_DESCRIPTION,example = PRODUCT_DESCRIPTION_EXAMPLE)
    private String description;
    @Schema(description = PRODUCT_PRICE_DESCRIPTION,example = PRODUCT_PRICE_EXAMPLE)
    private Double price;


}
