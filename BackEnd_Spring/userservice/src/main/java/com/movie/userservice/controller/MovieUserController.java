package com.movie.userservice.controller;

import com.movie.userservice.exception.MovieAlreadyAddedToFavouritesException;
import com.movie.userservice.exception.UserNotFoundException;
import com.movie.userservice.model.Movie;
import com.movie.userservice.service.MovieUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MovieUserController {

    MovieUserServiceImpl movieUserService;

    @Autowired
    public MovieUserController(MovieUserServiceImpl movieUserService) {
        this.movieUserService = movieUserService;
    }


    @PostMapping("/addFavourite")
    public ResponseEntity<?> addFavourite(@RequestBody Movie movie) {
            movieUserService.addToFavourites(movie);
            return new ResponseEntity<Movie>(movie, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteFavourite/userId/{userId}/movieId/{movieId}")
    public ResponseEntity<String> deleteFavourite(@PathVariable String userId, @PathVariable() int movieId) {
        try{
            movieUserService.deleteFromFavourites(userId, movieId);
            return new ResponseEntity<String>("Successfully deleted from Favourites", HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/getAllFavourites/userId/{userId}")
    public ResponseEntity<?> getAllFavourites(@PathVariable() String userId) {
        try{
        List<Movie> userFavourites = movieUserService.getAllFavourites(userId);
            return new ResponseEntity<List<Movie>>(userFavourites, HttpStatus.OK);
        } catch (UserNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}

