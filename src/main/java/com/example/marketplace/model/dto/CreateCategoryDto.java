package com.example.marketplace.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import static com.example.marketplace.constants.swagger.CategorySwaggerConstants.*;
import static com.example.marketplace.constants.validation.CategoryValidationConstants.CATEGORY_NAME_REQUIRED;

@Getter
@Setter
@Schema(title = CATEGORY_CREATE_DTO_TITLE)
public class CreateCategoryDto {
    @NotBlank(message = CATEGORY_NAME_REQUIRED)
    @Schema(description = NAME_OF_CATEGORY_DESCRIPTION,example = NAME_OF_CATEGORY_EXAMPLE)
    private String name;
}
