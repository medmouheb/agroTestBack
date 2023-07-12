package com.agrotech.api.exceptions;

public class EmptyFileException extends Exception {
    public EmptyFileException() {}
    public EmptyFileException(String message) {
        super(message);
    }
}

