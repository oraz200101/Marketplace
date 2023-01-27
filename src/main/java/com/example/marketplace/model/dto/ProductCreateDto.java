package com.example.marketplace.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

import static com.example.marketplace.constants.swagger.ProductSwaggerConstants.*;
import static com.example.marketplace.constants.validation.ProductValidationConstants.*;

@Getter
@Setter
@Schema(title = PRODUCT_CREATE_DTO_TITLE)
public class ProductCreateDto {
    @NotBlank(message = PRODUCT_NAME_REQUIRED_MESSAGE)
    @Schema(description =PRODUCT_NAME_DESCRIPTION,example =PRODUCT_NAME_EXAMPLE )
    private String name;
    @NotBlank(message = PRODUCT_DESCRIPTION_REQUIRED_MESSAGE)
    @Schema(description = PRODUCT_DESCRIPTION_DESCRIPTION,example =PRODUCT_DESCRIPTION_EXAMPLE )
    private String description;
    @NotNull(message = PRODUCT_COST_REQUIRED_MESSAGE)
    @Schema(description = PRODUCT_PRICE_DESCRIPTION,example =PRODUCT_PRICE_EXAMPLE)
    private Double price;

}
