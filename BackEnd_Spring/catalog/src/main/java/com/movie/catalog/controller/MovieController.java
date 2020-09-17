package com.movie.catalog.controller;


import com.movie.catalog.domain.Movie;
import com.movie.catalog.exception.MovieAlreadyExistsException;
import com.movie.catalog.exception.MovieNotFoundException;
import com.movie.catalog.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MovieController {

    private IMovieService movieService;
    private ResponseEntity responseEntity;

    @Autowired
    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    //Create a new movie
    @PostMapping("/addMovie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
        try {
            Movie createdMovie = movieService.addMovie(movie);
            responseEntity = new ResponseEntity(createdMovie, HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some internal error try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Delete movie from catalog
    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable int id) {
        try {
            Movie movie = movieService.getMovieById(id);
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity(movie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Update movie in catalog
    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable int id) {
        try {
            Movie fetchedMovie = movieService.getMovieById(id);
            fetchedMovie = movieService.updateMovie(movie, id);
            responseEntity = new ResponseEntity(fetchedMovie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Get all movies
    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {
        try {
            List<Movie> movieList = movieService.getAllMovies();
            responseEntity = new ResponseEntity(movieList, HttpStatus.FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some internal error try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Get movie by id
    @GetMapping("/movieId/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable int id) {
        try {
            Movie movie = movieService.getMovieById(id);
            responseEntity = new ResponseEntity(movie, HttpStatus.FOUND);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //Get movie by title
    @GetMapping("/movieTitle/{title}")
    public ResponseEntity<?> findMovieByTitle(@PathVariable String title){
        try{
            List<Movie> movieList = movieService.getMovieByTitle(title);
            responseEntity = new ResponseEntity(movieList, HttpStatus.FOUND);
        } catch (MovieNotFoundException e){
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
