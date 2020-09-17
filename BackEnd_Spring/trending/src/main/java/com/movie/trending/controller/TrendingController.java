package com.movie.trending.controller;


import com.movie.trending.domain.Movie;
import com.movie.trending.exception.MovieAlreadyExistsException;
import com.movie.trending.exception.MovieNotFoundException;
import com.movie.trending.service.ITrendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TrendingController {

    private ITrendingService movieService;
    private ResponseEntity responseEntity;

    @Autowired
    public TrendingController(ITrendingService movieService) {
        this.movieService = movieService;
    }

    //Add a new trending movie
    @PostMapping("/addTrending")
    public ResponseEntity<?> saveTrending(@RequestBody Movie movie) {
        try {
            Movie createdMovie = movieService.addTrendingMovie(movie);
            responseEntity = new ResponseEntity(createdMovie, HttpStatus.CREATED);
        } catch (MovieAlreadyExistsException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some internal error try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Delete movie from trending
    @DeleteMapping("/deleteTrending/{id}")
    public ResponseEntity<Movie> deleteTrending(@PathVariable int id) {
        try {
            Movie movie = movieService.getTrendingById(id);
            movieService.deleteTrendingMovie(id);
            responseEntity = new ResponseEntity(movie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Update movie in catalog
    @PutMapping("/updateTrending/{id}")
    public ResponseEntity<?> updateTrending(@RequestBody Movie movie, @PathVariable int id) {
        try {
            Movie fetchedMovie = movieService.getTrendingById(id);
            fetchedMovie = movieService.updateTrendingMovie(movie, id);
            responseEntity = new ResponseEntity(fetchedMovie, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    //Get all movies
    @GetMapping("/trendingMovies")
    public ResponseEntity<?> getAllTrending() {
        try {
            List<Movie> movieList = movieService.getAllTrendingMovies();
            responseEntity = new ResponseEntity(movieList, HttpStatus.FOUND);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Some internal error try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Get trending movie by id
    @GetMapping("/movieId/{id}")
    public ResponseEntity<Movie> findTrendingById(@PathVariable int id) {
        try {
            Movie movie = movieService.getTrendingById(id);
            responseEntity = new ResponseEntity(movie, HttpStatus.FOUND);
        } catch (MovieNotFoundException e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

}
