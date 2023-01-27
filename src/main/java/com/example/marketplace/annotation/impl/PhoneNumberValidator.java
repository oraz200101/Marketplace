package com.example.marketplace.annotation.impl;

import com.example.marketplace.annotation.annotation.PhoneNumber;
import com.example.marketplace.utils.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.marketplace.constants.validation.UserValidationConstants.USER_PHONE_FORMAT_INVALID_MESSAGE;
import static com.example.marketplace.constants.validation.UserValidationConstants.USER_PHONE_NUMBER_REQUIRED_MESSAGE;


public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber,String> {
   private boolean required;
    @Override
    public void initialize(PhoneNumber constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s != null && !s.isEmpty()) {
            Pattern p = Pattern.compile("[0-9]{11}");
            Matcher m = p.matcher(s);
            if (!m.matches())
                return ValidationUtils.setAnotherValidationErrorMessage(constraintValidatorContext, USER_PHONE_FORMAT_INVALID_MESSAGE);
        }
            if (required)
                return ValidationUtils.setAnotherValidationErrorMessage(constraintValidatorContext, USER_PHONE_NUMBER_REQUIRED_MESSAGE);
        return true;
        }
    }

