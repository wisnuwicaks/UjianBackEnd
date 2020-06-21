package com.ujianbackend.movie.entity;

import javax.persistence.*;

@Entity
@Table(name="movies_cat")
public class MovCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idMovie;
    private int idCategory;
}
