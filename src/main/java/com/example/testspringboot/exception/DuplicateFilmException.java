package com.example.testspringboot.exception;

public class DuplicateFilmException extends RuntimeException {
    public DuplicateFilmException(String message){
        super(message);
    }
}
