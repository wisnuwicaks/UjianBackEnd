package com.ujianbackend.movie.dao;

import com.ujianbackend.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie,Integer> {
}
