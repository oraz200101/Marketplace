package com.example.marketplace.exception.domain;

import com.example.marketplace.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.marketplace.constants.exception.UserExceptionConstants.USER_NOT_FOUND_BY_PHONE_NUMBER_CODE;
import static com.example.marketplace.constants.exception.UserExceptionConstants.USER_NOT_FOUND_BY_PHONE_NUMBER_MESSAGE;

public class UserNotFoundByPhoneNumberException extends CustomException {
    public UserNotFoundByPhoneNumberException(){
        super(USER_NOT_FOUND_BY_PHONE_NUMBER_MESSAGE, USER_NOT_FOUND_BY_PHONE_NUMBER_CODE, HttpStatus.NOT_FOUND);
    }
}
