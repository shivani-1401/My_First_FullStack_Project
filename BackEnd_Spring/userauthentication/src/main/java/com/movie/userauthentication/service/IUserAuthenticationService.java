package com.movie.userauthentication.service;

import com.movie.userauthentication.domain.User;
import com.movie.userauthentication.exception.UserAlreadyExistsException;
import com.movie.userauthentication.exception.UserNotFoundException;

public interface IUserAuthenticationService {

    public User findByIdAndPassword(String username, String password) throws UserNotFoundException;

    boolean saveUser(User user) throws UserAlreadyExistsException;

}
