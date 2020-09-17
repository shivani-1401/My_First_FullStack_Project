package com.movie.catalog.exception;

public class MovieAlreadyExistsException extends Exception{
    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}
