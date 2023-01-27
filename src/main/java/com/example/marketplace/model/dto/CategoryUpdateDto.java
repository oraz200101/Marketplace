package com.example.marketplace.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.marketplace.constants.swagger.BaseEntitySwaggerConstants.FIELD_ID_DESCRIPTION;
import static com.example.marketplace.constants.swagger.BaseEntitySwaggerConstants.FIELD_ID_EXAMPLE;
import static com.example.marketplace.constants.swagger.CategorySwaggerConstants.*;
import static com.example.marketplace.constants.validation.CategoryValidationConstants.CATEGORY_NAME_REQUIRED;

@Data
@Schema(title = CATEGORY_UPDATE_DTO_TITLE)
public class CategoryUpdateDto {
    @Schema(description = FIELD_ID_DESCRIPTION,example = FIELD_ID_EXAMPLE)
    private Long id;
    @NotBlank(message = CATEGORY_NAME_REQUIRED)
    @Schema(description = NAME_OF_CATEGORY_DESCRIPTION,example = NAME_OF_CATEGORY_EXAMPLE)
    private String name;
}
