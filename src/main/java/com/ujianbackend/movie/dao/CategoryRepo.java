package com.ujianbackend.movie.dao;

import com.ujianbackend.movie.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
