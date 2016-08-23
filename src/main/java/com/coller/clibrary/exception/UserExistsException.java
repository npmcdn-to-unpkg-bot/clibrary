package com.coller.clibrary.exception;

public class UserExistsException extends ClibraryException {

    public UserExistsException(){}
    public UserExistsException(String message){
        super(message);
    }
}
