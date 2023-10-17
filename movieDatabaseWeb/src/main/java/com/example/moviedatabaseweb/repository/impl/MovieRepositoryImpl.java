package com.example.moviedatabaseweb.repository.impl;



import com.example.moviedatabaseweb.bootstrap.Database;
import com.example.moviedatabaseweb.model.Movie;
import com.example.moviedatabaseweb.repository.MovieRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
    private List<Movie> movies;

    public MovieRepositoryImpl() {
        movies = new ArrayList<>();
    }
    @Override
    public List<Movie> getMovies() {
        return movies;
    }
    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    @Override
    public void reset(){
        ResultSet rs = Database.browseMovies();
        try{
            if(rs!=null){
                while(movies.size()>0){
                    movies.remove(0);
                }
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Movie movie = new Movie(id, name,description,image);

                    movies.add(movie);
                }

            }

        }catch (SQLException sqle){
            System.out.println("table not populated");
        }
    }
    @Override
    public List<Movie> searchMovies(String term) {
        return movies.stream().filter(
                m -> m.getMovieDescription().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))
                ||m.getMovieName().toLowerCase(Locale.ROOT).contains(term.toLowerCase(Locale.ROOT))
        ).collect(Collectors.toList());

    }
    @Override
    public List<Movie> searchMovieId(int id) {
        return movies.stream().filter(
                m -> m.getMovieId()==id
        ).collect(Collectors.toList());

    }
    @Override
    public Movie getMovieById(int movieId) {
        var filtered = movies.stream().filter(m -> Objects.equals(m.getMovieId(), movieId)).collect(Collectors.toList());
        if (filtered.size() > 0) {
            return filtered.get(0);
        }
        throw new IllegalStateException("movie not found with ID " + movieId);
    }
    @Override
    public void editMovie(int movieId, String movieName, String movieDescription, String movieImage)  {

        Movie movie=getMovieById(movieId);
        Movie newMovie=new Movie(movieId,movieName,movieDescription,movieImage);
        movies.set(movies.indexOf(movie),newMovie);

        Database.editMovie(movieId,movieName,movieDescription,movieImage);

    }
    @Override
    public void removeMovie(int id) {
        try {
            movies.remove(getMovieById(id));
            Database.removeMovie(id);
        }catch (SQLException sqle){
            System.out.println("Remove Error");
        }

    }
}
