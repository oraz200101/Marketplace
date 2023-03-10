package com.example.marketplace.exception.domain;

import com.example.marketplace.exception.CustomException;
import org.springframework.http.HttpStatus;

import static com.example.marketplace.constants.jwt.JwtConstants.JWT_DOESNT_VALID_CODE;
import static com.example.marketplace.constants.jwt.JwtConstants.JWT_DOESNT_VALID_MESSAGE;

public class JwtTokenInvalidException extends CustomException {

    public JwtTokenInvalidException() {
        super(JWT_DOESNT_VALID_MESSAGE, JWT_DOESNT_VALID_CODE, HttpStatus.UNAUTHORIZED);
    }
}
