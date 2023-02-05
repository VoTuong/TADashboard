package com.tadashboard.exceptions;

public class TargetNotValidException extends IllegalStateException{
    public TargetNotValidException(String target) {
        super(String.format("DriverMode %s not supported. Use either local or gird", target));
    }
}
