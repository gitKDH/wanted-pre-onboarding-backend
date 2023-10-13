package com.report.wantedpreonboardingbackend.exceptions;

public class DuplicateApplicationException extends RuntimeException{
    public DuplicateApplicationException(String message){
        super(message);
    }
}
