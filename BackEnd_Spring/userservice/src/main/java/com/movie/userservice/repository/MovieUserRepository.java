package com.movie.userservice.repository;

import com.movie.userservice.model.MovieUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieUserRepository extends MongoRepository<MovieUser, String> {
}
