package com.ra.md4projectapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataExistException extends Exception{
    private String field;
    public DataExistException(String message,String field) {
        super(message);
        this.field = field;
    }
}
