package com.movie.userauthentication.service;

import com.movie.userauthentication.domain.User;
import com.movie.userauthentication.exception.UserAlreadyExistsException;
import com.movie.userauthentication.exception.UserNotFoundException;
import com.movie.userauthentication.repository.UserAuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationServiceImpl implements IUserAuthenticationService{

    private UserAuthenticationRepository userAutheticationRepository;
    @Autowired
    public UserAuthenticationServiceImpl(UserAuthenticationRepository userAutheticationRepository){
        this.userAutheticationRepository = userAutheticationRepository;
    }

    @Override
    public User findByIdAndPassword(String username, String password) throws UserNotFoundException {
        User currentUser = this.userAutheticationRepository.findByIdAndPassword(username, password);
        if (currentUser != null) {
            return currentUser;
        } else {
            throw new UserNotFoundException("User is not registered");
        }
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
        boolean flag = false;
        Optional optional = userAutheticationRepository.findById(user.getId());
        if (optional.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        } else {
            User savedUser = userAutheticationRepository.save(user);
            flag = true;
        }
        return flag;
    }
}
