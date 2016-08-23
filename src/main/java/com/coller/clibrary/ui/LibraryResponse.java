package com.coller.clibrary.ui;

import lombok.Data;

@Data
public class LibraryResponse {

    private Object data;

    private String error;

    public LibraryResponse(){}

    public LibraryResponse(Object data){
        this(data, null);
    }

    public LibraryResponse(String error){
        this(null, error);
    }

    public LibraryResponse(Object data, String error){
        this.data = data;
        this.error = error;
    }
}
