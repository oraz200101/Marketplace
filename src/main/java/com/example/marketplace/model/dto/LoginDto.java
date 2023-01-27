package com.example.marketplace.model.dto;

import com.example.marketplace.annotation.annotation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Setter;

import static com.example.marketplace.constants.swagger.UserSwaggerConstants.*;
import static com.example.marketplace.constants.validation.UserValidationConstants.USER_PASSWORD_REQUIRED_MESSAGE;

@Data
@Schema(title = LOGIN_DTO_TITLE)
public class LoginDto {
    @PhoneNumber(required = true)
    @Schema(description = USER_PHONE_NUMBER_DESCRIPTION,example = USER_PHONE_NUMBER_EXAMPLE)
    private String phoneNumber;
    @NotBlank(message = USER_PASSWORD_REQUIRED_MESSAGE )
    @Schema(description = USER_PASSWORD_DESCRIPTION,example = USER_PASSWORD_EXAMPLE)
    private String password;

}
