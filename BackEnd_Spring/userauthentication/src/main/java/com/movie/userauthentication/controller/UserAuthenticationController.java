package com.movie.userauthentication.controller;

import com.movie.userauthentication.domain.User;
import com.movie.userauthentication.exception.UserAlreadyExistsException;
import com.movie.userauthentication.service.IUserAuthenticationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class UserAuthenticationController {

    private Map<String,String> map = new HashMap<>();
    private IUserAuthenticationService authicationService;
    @Autowired
    public UserAuthenticationController(IUserAuthenticationService authicationService) {
        this.authicationService = authicationService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        try {
            this.authicationService.saveUser(user);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PostMapping("/auth/login")
    public ResponseEntity userLogin(@RequestBody User user){

        try {
            String jwtToken = generateToken(user.getId(), user.getPassword());
            map.put("message" , "User successfully logged in");
            map.put("user", user.getId());
            map.put("token",jwtToken);
        } catch (Exception e) {
            map.put("message",e.getMessage());
            map.put("token",null);
            return new ResponseEntity(map, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity(map,HttpStatus.OK);


    }

    private String generateToken(String id,String password) throws Exception {
        String jwtToken="";
        if(id == null || password == null){
            throw new Exception("Please send valid username or password");
        }
        //validate the user from db
        User user =  authicationService.findByIdAndPassword(id,password);
        if(user==null){
            throw new Exception("Invalid credentials");
        }
        else
        {
            jwtToken = Jwts.builder()
                    .setSubject(id)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 300000000))
                    .signWith(SignatureAlgorithm.HS256,"secretkey")
                    .compact();
        }
        return jwtToken;
    }

}
