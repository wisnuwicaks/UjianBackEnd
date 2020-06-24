package com.ujianbackend.movie.controller;

import com.ujianbackend.movie.dao.CategoryRepo;
import com.ujianbackend.movie.dao.MovieRepo;
import com.ujianbackend.movie.entity.Category;
import com.ujianbackend.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private MovieRepo movieRepo;

    @GetMapping("/allcategories")
    public Iterable<Category> getAllCategories(){
        return categoryRepo.findAll();
    }

    @PostMapping("/addcategory")
    public Category addMovie(@RequestBody Category category){
        return categoryRepo.save(category);
    }

    @PutMapping("update/{categoryId}")
    public Category updateMovie(@PathVariable int categoryId,@RequestBody Category category){
        Category findCategory = categoryRepo.findById(categoryId).get();
        category.setId(categoryId);
        return categoryRepo.save(category);
    }

    @DeleteMapping("delete/{categoryId}")
    public void deleteMovie(@PathVariable int categoryId){
        Category findCategory = categoryRepo.findById(categoryId).get();
        findCategory.getMovies().forEach(movie -> {
            movie.getCategories().remove(findCategory);
            movieRepo.save(movie);
        });

        findCategory.setMovies(null);
        categoryRepo.deleteById(categoryId);
    }

    @GetMapping("/{categoryId}/movies")
    public List<Movie> getMovieWithCategoryId(@PathVariable int categoryId){
        Category findCat = categoryRepo.findById(categoryId).get();
        return findCat.getMovies();
    }


}
