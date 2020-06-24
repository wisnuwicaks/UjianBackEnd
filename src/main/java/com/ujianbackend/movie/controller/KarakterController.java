package com.ujianbackend.movie.controller;


import com.ujianbackend.movie.dao.KarakterRepo;
import com.ujianbackend.movie.dao.MovieRepo;
import com.ujianbackend.movie.entity.Karakter;
import com.ujianbackend.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/characters")
public class KarakterController {

    @Autowired
    private KarakterRepo karakterRepo;
    @Autowired
    private MovieRepo movieRepo;


    @GetMapping("/allcharacters")
    public Iterable<Karakter> getAllCharacters(){
        return karakterRepo.findAll();
    }

    @PostMapping("/addcharacter")
    public Karakter addCharater(@RequestBody Karakter karakter){
        return karakterRepo.save(karakter);
    }

    @PutMapping("/updatecharacter/{characterId}")
    public Karakter updateCharacter(@RequestBody Karakter karakter, @PathVariable int characterId){
        Karakter findKarakter = karakterRepo.findById(characterId).get();
        karakter.setId(characterId);
        return  karakterRepo.save(karakter);
    }

    @PutMapping("/{characterId}/movie/{movieId}")
    public Karakter addMovieToCharacter(@PathVariable int characterId, @PathVariable int movieId){
        Karakter findCharacter = karakterRepo.findById(characterId).get();
        Movie findMovie = movieRepo.findById(movieId).get();

        findCharacter.setMovie(findMovie);

        return karakterRepo.save(findCharacter);

    }


}
