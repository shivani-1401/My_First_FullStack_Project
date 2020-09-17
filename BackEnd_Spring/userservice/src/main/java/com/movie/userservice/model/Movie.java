package com.movie.userservice.model;


public class Movie {

    private int id;
    private String originalTitle;
    private String releaseDate;
    private String voteAverage;
    private String posterPath;
    private String movieAddedBy;

    public Movie() {
    }

    public Movie(int id, String originalTitle, String releaseDate, String voteAverage, String posterPath, String movieAddedBy) {
        this.id = id;
        this.originalTitle = originalTitle;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.posterPath = posterPath;
        this.movieAddedBy = movieAddedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getMovieAddedBy() {
        return movieAddedBy;
    }

    public void setMovieAddedBy(String movieAddedBy) {
        this.movieAddedBy = movieAddedBy;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", originalTitle='" + originalTitle + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAverage='" + voteAverage + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", movieAddedBy='" + movieAddedBy + '\'' +
                '}';
    }
}

