package com.movie.catalog.service;

import com.movie.catalog.domain.Movie;
import com.movie.catalog.exception.MovieAlreadyExistsException;
import com.movie.catalog.exception.MovieNotFoundException;
import com.movie.catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements IMovieService {

    private MovieRepository movieRepository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie addMovie(Movie movie) throws MovieAlreadyExistsException {
        Movie addedMovie = null;
        Optional<Movie> optional = movieRepository.findById(movie.getId());
        if(optional.isPresent()){
            throw new MovieAlreadyExistsException("Movie with the given id already exists");
        }
        else{
            addedMovie = movieRepository.save(movie);
        }
        return addedMovie;
    }

    @Override
    public boolean deleteMovie(int movieId) throws MovieNotFoundException {
        boolean deletedMovie = false;
        Movie fetchedMovie = movieRepository.findById(movieId).get();
        if(fetchedMovie==null){
            throw new MovieNotFoundException("Given movie doesn't exist");
        }
        else{
            movieRepository.deleteById(movieId);
            deletedMovie = true;
        }
        return deletedMovie;
    }

    @Override
    public Movie updateMovie(Movie movie, int movieId) throws MovieNotFoundException {
        Movie fetchedMovie = movieRepository.findById(movieId).get();
        if(fetchedMovie==null){
            throw new MovieNotFoundException("Movie with the given id doesn't exist");
        }
        else{
            fetchedMovie.setOriginalTitle(movie.getOriginalTitle());
            fetchedMovie.setReleaseDate(movie.getReleaseDate());
            fetchedMovie.setVoteAverage(movie.getVoteAverage());
            fetchedMovie.setPosterPath(movie.getPosterPath());
            movieRepository.save(fetchedMovie);
        }
        return fetchedMovie;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(int movieId) throws MovieNotFoundException {
        Movie fetchedMovie = null;
        fetchedMovie = movieRepository.findById(movieId).get();
        if (fetchedMovie == null) {
            throw new MovieNotFoundException("Movie with the given id doesn't exist");
        }
        return fetchedMovie;
    }

    @Override
    public List<Movie> getMovieByTitle(String movieTitle) throws MovieNotFoundException {
        List<Movie> fetchedMovies = movieRepository.findByOriginalTitle(movieTitle);
        if(fetchedMovies == null){
            throw new MovieNotFoundException("Movie with the given title doesn't exist");
        }
        return fetchedMovies;
    }
}
