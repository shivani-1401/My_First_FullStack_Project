package com.movie.trending.exception;

public class MovieAlreadyExistsException extends Exception{
    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}
