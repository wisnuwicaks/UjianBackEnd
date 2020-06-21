package com.ujianbackend.movie.entity;

import javax.persistence.*;

@Entity
@Table(name = "characters")
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
