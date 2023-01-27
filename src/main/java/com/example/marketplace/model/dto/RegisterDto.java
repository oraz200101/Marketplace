package com.example.marketplace.model.dto;

import com.example.marketplace.annotation.annotation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import static com.example.marketplace.constants.swagger.UserSwaggerConstants.*;
import static com.example.marketplace.constants.swagger.UserSwaggerConstants.USER_FIRSTNAME_EXAMPLE;
import static com.example.marketplace.constants.validation.UserValidationConstants.*;

@Data
@Schema(title = REGISTER_DTO_TITLE)
public class RegisterDto {
    @NotBlank(message = USER_FIRST_NAME_REQUIRED_MESSAGE)
    @Schema(description = USER_FIRSTNAME_DESCRIPTION,example = USER_FIRSTNAME_EXAMPLE)
    private String firstName;
    @NotBlank(message = USER_LAST_NAME_REQUIRED_MESSAGE)
    @Schema(description = USER_LASTNAME_DESCRIPTION,example = USER_LASTNAME_EXAMPLE)
    private String lastname;
    @NotBlank(message = USER_PASSWORD_REQUIRED_MESSAGE)
    @Schema(description = USER_PASSWORD_DESCRIPTION,example = USER_PASSWORD_EXAMPLE)
    private String password;
    @Schema(description =USER_PASSWORD_MATCHING_DESCRIPTION,example = USER_PASSWORD_MATCHING_EXAMPLE)
    private String matchingPassword;
    @PhoneNumber(required = true)
    @Schema(description = USER_PHONE_NUMBER_DESCRIPTION,example = USER_PHONE_NUMBER_EXAMPLE)
    private String phoneNumber;

}
