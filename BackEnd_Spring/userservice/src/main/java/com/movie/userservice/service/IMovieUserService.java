package com.movie.userservice.service;

import com.movie.userservice.exception.MovieAlreadyAddedToFavouritesException;
import com.movie.userservice.exception.UserNotFoundException;
import com.movie.userservice.model.Movie;
import com.movie.userservice.model.MovieUser;

import java.util.List;

public interface IMovieUserService {


    boolean addToFavourites(Movie movie);

    boolean deleteFromFavourites(String userId, int id) throws UserNotFoundException;

    List<Movie> getAllFavourites(String userId) throws UserNotFoundException;

}
