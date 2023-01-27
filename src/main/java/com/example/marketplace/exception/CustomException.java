package com.example.marketplace.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomException extends RuntimeException{
    private static final long serialVersionUID =1L;
    private String code;
    private HttpStatus httpStatus;

    public CustomException(String message,String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
