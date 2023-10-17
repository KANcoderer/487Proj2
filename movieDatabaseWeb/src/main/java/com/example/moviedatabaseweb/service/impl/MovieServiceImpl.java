package com.example.moviedatabaseweb.service.impl;

import com.example.moviedatabaseweb.bootstrap.Database;
import com.example.moviedatabaseweb.model.Movie;
import com.example.moviedatabaseweb.repository.MovieRepository;
import com.example.moviedatabaseweb.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> getMovies() {

        return movieRepository.getMovies();
    }
    @Override
    public List<Movie> searchMovies(String term) {
        if (! StringUtils.hasText(term)) {
            throw new IllegalArgumentException("term can't be empty");
        }
        return movieRepository.searchMovies(term);
    }
    @Override
    public List<Movie> searchMovieId(int id) {
        return movieRepository.searchMovieId(id);
    }
    @Override
    public void deleteMovie(int id){
        movieRepository.removeMovie(id);

    }
    @Override
    public Movie getMovieById(int movieId) {
        if (movieId <=0) {
            throw new IllegalArgumentException("game ID is required");
        }

        return movieRepository.getMovieById(movieId);
    }
    @Override
    public void editMovie(int movieId, String movieName, String movieDescription, String movieImage)  {
        movieRepository.editMovie(movieId,movieName,movieDescription,movieImage);
    }
    @Override
    public void reset(){
        movieRepository.reset();
    }
    @Override
    public void addMovie(String movieName, String movieDescription, String movieImage)  {

        if (! StringUtils.hasText(movieName)) {
            throw new IllegalArgumentException("movie name is required");
        }
        if (! StringUtils.hasText(movieDescription)) {
            throw new IllegalArgumentException("movie description is required");
        }
        if (! StringUtils.hasText(movieImage)) {
            throw new IllegalArgumentException("movie image link is required");
        }
        Database.addMovie(movieName,movieDescription,movieImage);
        ResultSet rs;
        try {
            rs= Database.GetMaxID();
            if(rs!=null) {
                if(rs.next()){
                    Movie movie = new Movie(rs.getInt("max(id)"), movieName, movieDescription, movieImage);
                    movieRepository.addMovie(movie);
                }
            }else{
                throw new SQLException();
            }
        }catch (SQLException sqle){
            System.err.println("No max id");
        }
    }
}
