package com.movie.trending.repository;

import com.movie.trending.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrendingMovieRepository extends MongoRepository<Movie, Integer> {
}
