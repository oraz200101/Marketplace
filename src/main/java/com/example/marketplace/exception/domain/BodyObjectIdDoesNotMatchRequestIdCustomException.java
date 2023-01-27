package com.example.marketplace.exception.domain;


import com.example.marketplace.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.marketplace.constants.exception.BaseExceptionConstants.BODY_OBJECT_ID_DOES_NOT_MATCH_REQUEST_ID_CODE;
import static com.example.marketplace.constants.exception.BaseExceptionConstants.BODY_OBJECT_ID_DOES_NOT_MATCH_REQUEST_ID_MESSAGE;

public class BodyObjectIdDoesNotMatchRequestIdCustomException extends CustomException
{
    public BodyObjectIdDoesNotMatchRequestIdCustomException() {
        super(BODY_OBJECT_ID_DOES_NOT_MATCH_REQUEST_ID_MESSAGE, BODY_OBJECT_ID_DOES_NOT_MATCH_REQUEST_ID_CODE, HttpStatus.BAD_REQUEST);
    }
}
