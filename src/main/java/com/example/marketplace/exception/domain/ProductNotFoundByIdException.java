package com.example.marketplace.exception.domain;

import com.example.marketplace.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.marketplace.constants.exception.ProductExceptionConstants.PRODUCT_NOT_FOUND_BY_ID_CODE;
import static com.example.marketplace.constants.exception.ProductExceptionConstants.PRODUCT_NOT_FOUND_BY_ID_MESSAGE;

public class ProductNotFoundByIdException extends CustomException {
    public ProductNotFoundByIdException() {
        super(PRODUCT_NOT_FOUND_BY_ID_MESSAGE,PRODUCT_NOT_FOUND_BY_ID_CODE,HttpStatus.NOT_FOUND);
    }
}
