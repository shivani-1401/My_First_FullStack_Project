package com.movie.userservice.service;

import com.movie.userservice.exception.MovieAlreadyAddedToFavouritesException;
import com.movie.userservice.exception.UserNotFoundException;
import com.movie.userservice.model.Movie;
import com.movie.userservice.model.MovieUser;
import com.movie.userservice.repository.MovieUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieUserServiceImpl implements IMovieUserService {

    MovieUserRepository repository;

    @Autowired
    public MovieUserServiceImpl(MovieUserRepository repository) {
        this.repository = repository;
    }

    private MovieUser movieUser = null;
    private List<Movie> movies = null;

    @Override
    public boolean addToFavourites(Movie movie){

        int counter = 1;
        boolean status = false;
        movieUser = new MovieUser();
        movies = new ArrayList<>();
        if (repository.findById(movie.getMovieAddedBy()).isPresent()) {
            movies = repository.findById(movie.getMovieAddedBy()).get().getFavouritesList();
            Iterator iterator = movies.iterator();
            Movie movie1 = new Movie();
            while (iterator.hasNext()) {
                movie1 = (Movie) iterator.next();
            }
            movie.setId(movie1.getId() + counter);
            movies.add(movie);
            movieUser.setId(movie.getMovieAddedBy());
            movieUser.setFavouritesList(movies);
            if (repository.save(movieUser) != null) {
                status = true;
            }
        } else {
            movie.setId(counter);
            movies.add(movie);
            movieUser.setId(movie.getMovieAddedBy());
            movieUser.setFavouritesList(movies);
            if (repository.insert(movieUser) != null) {
                status = true;
            }
        }
        return status;

    }

    @Override
    public boolean deleteFromFavourites(String userId, int id) throws UserNotFoundException{
        boolean status = false;
        movieUser = new MovieUser();
        Optional optionalUser = repository.findById(userId);
        if(optionalUser.isPresent()){
            movies = repository.findById(userId).get().getFavouritesList();
            if (!movies.isEmpty()) {
                Iterator iterator = movies.listIterator();
                while (iterator.hasNext()) {
                    Movie movie = (Movie) iterator.next();
                    if (movie.getId() == id)
                        iterator.remove();
                }
                movieUser.setId(userId);
                movieUser.setFavouritesList(movies);
                repository.save(movieUser);
                status = true;
            }
        }
        else{
            throw new UserNotFoundException("User with the given id is not found");
        }

        return status;

    }

    @Override
    public List<Movie> getAllFavourites(String userId) throws UserNotFoundException
    {
        List<Movie> allFavourites = null;
        Optional<MovieUser> optionalUser = repository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new UserNotFoundException("User with the given id doesn't exist");
        }
        else{
            allFavourites = repository.findById(userId).get().getFavouritesList();
        }
        return allFavourites;
    }
}

