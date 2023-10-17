package com.example.moviedatabaseweb.model;




public class Movie {
    private int movieId;
    private String movieName;
    private String movieDescription;
    private String movieImage;

    public Movie(int movieId, String movieName, String movieDescription, String movieImage) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieDescription = movieDescription;
        this.movieImage = movieImage;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieDescription() {
        return movieDescription;
    }

    public void setMovieDescription(String movieDescription) {
        this.movieDescription = movieDescription;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }
}
