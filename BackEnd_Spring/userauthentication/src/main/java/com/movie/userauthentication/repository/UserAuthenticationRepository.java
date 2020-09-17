package com.movie.userauthentication.repository;

import com.movie.userauthentication.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthenticationRepository extends MongoRepository<User, String> {

    User findByIdAndPassword(String id, String password);

}
