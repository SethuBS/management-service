package com.payu.management.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidBookTypeException extends RuntimeException {
    public InvalidBookTypeException(String message) {
        super(message);
    }
}
