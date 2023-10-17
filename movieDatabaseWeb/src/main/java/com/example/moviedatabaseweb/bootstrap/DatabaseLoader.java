package com.example.moviedatabaseweb.bootstrap;



import com.example.moviedatabaseweb.model.Movie;
import com.example.moviedatabaseweb.repository.MovieRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final MovieRepository movieRepository;

    public DatabaseLoader(MovieRepository movieRepository) {

        // developer managed
        this.movieRepository = movieRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Database.connect();
        //LinkedList<Movie> movies = new LinkedList<Movie>();
        ResultSet rs = Database.browseMovies();
        try{
            if(rs!=null){
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    String image = rs.getString("image");
                    Movie movie = new Movie(id, name,description,image);

                    movieRepository.addMovie(movie);
                }

            }

        }catch (SQLException sqle){
            System.out.println("table not populated");
        }
        // Fetch each row from the result set
    }
}