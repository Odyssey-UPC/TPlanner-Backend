package com.upc.tplanner.TPlanner.utils.exception;

public class ValidationException extends RuntimeException{
    public ValidationException() {
        super();
    }

    public ValidationException(String message){
        super(message);
    }

}
