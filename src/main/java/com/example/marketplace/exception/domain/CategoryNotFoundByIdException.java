package com.example.marketplace.exception.domain;

import com.example.marketplace.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.marketplace.constants.exception.CategoryExceptionConstants.CATEGORY_NOT_FOUND_BY_ID_CODE;
import static com.example.marketplace.constants.exception.CategoryExceptionConstants.CATEGORY_NOT_FOUND_BY_ID_MESSAGE;

public class CategoryNotFoundByIdException extends CustomException {
    public CategoryNotFoundByIdException(){
        super(CATEGORY_NOT_FOUND_BY_ID_MESSAGE,CATEGORY_NOT_FOUND_BY_ID_CODE, HttpStatus.NOT_FOUND);
    }
}
