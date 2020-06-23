package com.ujianbackend.movie.controller;

import com.ujianbackend.movie.dao.CategoryRepo;
import com.ujianbackend.movie.dao.MovieRepo;
import com.ujianbackend.movie.entity.Category;
import com.ujianbackend.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;


    @Autowired
    private CategoryRepo categoryRepo;

    @GetMapping("/allmovies")
    public Iterable<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    @PostMapping("/addmovie")
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepo.save(movie);
    }

    @PostMapping("/{movieId}/categories/{categoryId}")
    public Movie addMovieCategory (@PathVariable int movieId, @PathVariable int categoryId){
        Category findCategory = categoryRepo.findById(categoryId).get();
        Movie findMovie = movieRepo.findById(movieId).get();

        System.out.println(findMovie);
        System.out.println(findMovie.getCategories().toString());
        findMovie.getCategories().add(findCategory);
        return movieRepo.save(findMovie);

    }


    @PutMapping("update/{movieId}")
    public Movie updateMovie(@PathVariable int movieId,@RequestBody Movie movie){
        Movie findMovie = movieRepo.findById(movieId).get();
        movie.setId(movieId);
        return movieRepo.save(movie);
    }

    @DeleteMapping("delete/{movieId}")
    public void deleteMovie(@PathVariable int movieId){
        Movie findMovie = movieRepo.findById(movieId).get();
        findMovie.getCategories().forEach(category -> {
            List<Movie> movieCategories = category.getMovies();
            movieCategories.remove(findMovie);
            categoryRepo.save(category);
        });

        findMovie.setCategories(null);

        movieRepo.deleteById(movieId);


    }
}
