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

    //route untuk menampilkan semua movie
    @GetMapping("/allmovies")
    public Iterable<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    //Route untuk add movie
    @PostMapping("/addmovie")
    public Movie addMovie(@RequestBody Movie movie){
        return movieRepo.save(movie);
    }

    //Route untuk menambahkan category ke movie tertentu
    @PostMapping("/{movieId}/categories/{categoryId}")
    public Movie addMovieCategory (@PathVariable int movieId, @PathVariable int categoryId){
        Category findCategory = categoryRepo.findById(categoryId).get();
        Movie findMovie = movieRepo.findById(movieId).get();
        findMovie.getCategories().add(findCategory);
        return movieRepo.save(findMovie);

    }

    //Route untuk update/edit movie
    @PutMapping("update/{movieId}")
    public Movie updateMovie(@PathVariable int movieId,@RequestBody Movie movie){
        Movie findMovie = movieRepo.findById(movieId).get();
        movie.setId(movieId);
        movie.setCategories(findMovie.getCategories());
        return movieRepo.save(movie);
    }

    //Route untuk delete movie serta relasinya dengan category
    @DeleteMapping("delete/{movieId}")
    public void deleteMovie(@PathVariable int movieId){
        Movie findMovie = movieRepo.findById(movieId).get();
        findMovie.getCategories().forEach(category -> {

            category.getMovies().remove(findMovie);
            categoryRepo.save(category);
        });

        findMovie.setCategories(null);

        movieRepo.deleteById(movieId);

    }



    //route untuk delete category dari movie
    @PutMapping("/{movieId}/deletecategory/{categoryId}")
    public Movie removeCategoryFromMovie(@PathVariable int movieId, @PathVariable int categoryId){
        Movie findMovie = movieRepo.findById(movieId).get();
        Category findCategory = categoryRepo.findById(categoryId).get();
        if(findMovie.getCategories().contains(findCategory)){
            findMovie.getCategories().remove(findCategory);
            movieRepo.save(findMovie);
        }
//        findMovie.getCategories().forEach(category -> {
//            if(category.equals(findCategory)){
//                findMovie.getCategories().remove(category);
//                movieRepo.save(findMovie);
//            }
//        });

        return findMovie;


    }



}
