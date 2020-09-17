package com.movie.userservice.exception;

public class MovieAlreadyAddedToFavouritesException extends Exception {
    public MovieAlreadyAddedToFavouritesException(String message) {
        super(message);
    }
}
