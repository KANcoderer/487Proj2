package com.example.moviedatabaseweb.repository;





import com.example.moviedatabaseweb.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getMovies();

    void addMovie(Movie movie);

    List<Movie> searchMovies(String term);
    List<Movie> searchMovieId(int id);
    void removeMovie(int id);
    void editMovie(int movieId, String movieName, String movieDescription, String movieImage);
    Movie getMovieById(int movieId);
    void reset();
}
