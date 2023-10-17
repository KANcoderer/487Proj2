package com.example.moviedatabaseweb.service;




import com.example.moviedatabaseweb.model.Movie;

import java.util.List;


public interface MovieService {
    List<Movie> getMovies();

    List<Movie> searchMovies(String term);

    List<Movie> searchMovieId(int id);

    void deleteMovie(int id);
    void addMovie(String movieName, String movieDescription, String movieImage);
    void editMovie(int id, String movieName, String movieDescription, String movieImage);
    Movie getMovieById(int movieId);
    void reset();
}
