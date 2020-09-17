package com.movie.catalog.repository;

import com.movie.catalog.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer> {

    public List<Movie> findByOriginalTitle(String title);

}
