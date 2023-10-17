package com.example.moviedatabaseweb.controller;


import com.example.moviedatabaseweb.model.Movie;
import com.example.moviedatabaseweb.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/add")
    public String addMovieView(Model model) {
        return "addMovie";
    }

    @PostMapping("/movie/add")
    public String addMovieSubmit(Model model, @RequestParam String movieName, @RequestParam String movieDescription, @RequestParam String movieImage) {
        try {
            movieService.addMovie(movieName, movieDescription, movieImage);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "addMovie";
        }

        model.addAttribute("successMessage", "The movie was successfully saved");
        return "addMovie";
    }

    @GetMapping("/movie/edit/{movieId}")
    public String editMovieView(@PathVariable int movieId, Model model) {
        Movie movie = movieService.getMovieById(movieId);
        model.addAttribute("movieName", movie.getMovieName());
        model.addAttribute("movieImage", movie.getMovieImage());
        model.addAttribute("movieDescription", movie.getMovieDescription());
        return "editMovie";
    }
    @PostMapping("/movie/edit")
    public String editMovieSubmit(Model model, @RequestParam Integer movieId, @RequestParam String movieName, @RequestParam String movieDescription, @RequestParam String movieImage) {
        try {
            if(movieId==null){
                throw new Exception();
            }
            movieService.editMovie(movieId, movieName, movieDescription, movieImage);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "editMovie";
        }

        model.addAttribute("successMessage", "The movie was successfully saved");
        return "redirect:/";
    }

}
