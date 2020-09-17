package com.movie.catalog.service;

import com.movie.catalog.domain.Movie;
import com.movie.catalog.exception.MovieAlreadyExistsException;
import com.movie.catalog.exception.MovieNotFoundException;

import java.util.List;

public interface IMovieService {

    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException;

    public boolean deleteMovie(int movieId) throws MovieNotFoundException;

    public Movie updateMovie(Movie movie, int movieId) throws MovieNotFoundException;

    public List<Movie> getAllMovies();

    public Movie getMovieById(int movieId) throws MovieNotFoundException;

    public List<Movie> getMovieByTitle(String movieTitle) throws MovieNotFoundException;
}
