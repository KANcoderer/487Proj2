package com.example.moviedatabaseweb.controller;



import com.example.moviedatabaseweb.model.Movie;
import com.example.moviedatabaseweb.service.MovieService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class IndexController {

    private final MovieService movieService;

    public IndexController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "index";
    }
    @RequestMapping("/reset")
    public String reset(Model model) {
        movieService.reset();
        return "redirect:/";
    }
    @RequestMapping("/search")
    public String searchMovies(@RequestParam String term, Model model) {
        List<Movie> movies = movieService.searchMovies(term);
        model.addAttribute("movies", movies);
        return "index";
    }
    @RequestMapping("/searchId")
    public String searchMovieId(@RequestParam String id, Model model) {
        int movieId;
        try{
            movieId=Integer.parseInt(id);
        }catch (NumberFormatException nfe){
            movieId=-1;
        }
        List<Movie> movies = movieService.searchMovieId(movieId);
        model.addAttribute("movies", movies);
        return "index";
    }
    @RequestMapping("/sortName")
    public String sortMovieNames(Model model) {

        List<Movie> movies = movieService.getMovies();
        movies.sort(Comparator.comparing(Movie::getMovieName));


        model.addAttribute("movies", movies);
        return "index";
    }
    @RequestMapping("/sortId")
    public String sortMovieIds(Model model) {
        List<Movie> movies = movieService.getMovies();
        movies.sort(Comparator.comparing(Movie::getMovieId));

        model.addAttribute("movies", movies);
        return "index";
    }
    @RequestMapping("/delete/{movieId}")
    public String removeMovieView(@PathVariable int movieId, Model model) {
        movieService.deleteMovie(movieId);
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "index";
    }


}

