package com.airnz.email.exceptions;

public class InvalidRequestException extends Exception{

    public InvalidRequestException(String errorMessage) {
        super(errorMessage);
    }
}
