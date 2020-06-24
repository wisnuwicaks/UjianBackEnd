package com.ujianbackend.movie.dao;

import com.ujianbackend.movie.entity.Karakter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KarakterRepo extends JpaRepository<Karakter, Integer> {
}
