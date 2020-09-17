package com.movie.trending.service;

import com.movie.trending.domain.Movie;
import com.movie.trending.repository.TrendingMovieRepository;
import com.movie.trending.exception.MovieAlreadyExistsException;
import com.movie.trending.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrendingServiceImpl implements ITrendingService {

    private TrendingMovieRepository movieRepository;

    @Autowired
    public TrendingServiceImpl(TrendingMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie addTrendingMovie(Movie movie) throws MovieAlreadyExistsException {
        Movie addedMovie = null;
        Optional<Movie> optional = movieRepository.findById(movie.getId());
        if (optional.isPresent()) {
            throw new MovieAlreadyExistsException("Movie with the given id already exists in trending list");
        } else {
            addedMovie = movieRepository.save(movie);
        }
        return addedMovie;
    }

    @Override
    public boolean deleteTrendingMovie(int movieId) throws MovieNotFoundException {
        boolean deletedMovie = false;
        Movie fetchedMovie = movieRepository.findById(movieId).get();
        if (fetchedMovie == null) {
            throw new MovieNotFoundException("Given movie doesn't exist in trending list");
        } else {
            movieRepository.deleteById(movieId);
            deletedMovie = true;
        }
        return deletedMovie;
    }

    @Override
    public Movie updateTrendingMovie(Movie movie, int movieId) throws MovieNotFoundException {
        Movie fetchedMovie = movieRepository.findById(movieId).get();
        if (fetchedMovie == null) {
            throw new MovieNotFoundException("Movie with the given id doesn't exist in trending list");
        } else {
            fetchedMovie.setOriginalTitle(movie.getOriginalTitle());
            fetchedMovie.setReleaseDate(movie.getReleaseDate());
            fetchedMovie.setVoteAverage(movie.getVoteAverage());
            fetchedMovie.setPosterPath(movie.getPosterPath());
            movieRepository.save(fetchedMovie);
        }
        return fetchedMovie;
    }

    @Override
    public List<Movie> getAllTrendingMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getTrendingById(int movieId) throws MovieNotFoundException {
        Movie fetchedMovie = null;
        fetchedMovie = movieRepository.findById(movieId).get();
        if (fetchedMovie == null) {
            throw new MovieNotFoundException("Movie with the given id doesn't exist");
        }
        return fetchedMovie;
    }
}

