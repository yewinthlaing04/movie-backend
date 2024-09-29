package com.ye.movie.moviebackend.repository;

import com.ye.movie.moviebackend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie , Integer > {

}
