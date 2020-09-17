package com.movie.userservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document
public class MovieUser {

    @Id
    private String id;
    private List<Movie> favouritesList;

    public MovieUser() {
    }

    public MovieUser(String id, List<Movie> favouritesList) {
        this.id = id;
        this.favouritesList = favouritesList;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Movie> getFavouritesList() {
        return favouritesList;
    }

    public void setFavouritesList(List<Movie> favouritesList) {
        this.favouritesList = favouritesList;
    }

    @Override
    public String toString() {
        return "MovieUser{" +
                "id='" + id + '\'' +
                ", favouritesList=" + favouritesList +
                '}';
    }
}
