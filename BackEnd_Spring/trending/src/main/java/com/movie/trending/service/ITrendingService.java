package com.movie.trending.service;

import com.movie.trending.domain.Movie;
import com.movie.trending.exception.MovieNotFoundException;
import com.movie.trending.exception.MovieAlreadyExistsException;

import java.util.List;

public interface ITrendingService {

    public Movie addTrendingMovie(Movie movie) throws MovieAlreadyExistsException;

    public boolean deleteTrendingMovie(int movieId) throws MovieNotFoundException;

    public Movie updateTrendingMovie(Movie movie, int movieId) throws MovieNotFoundException;

    public List<Movie> getAllTrendingMovies();

    public Movie getTrendingById(int movieId) throws MovieNotFoundException;
}
