package com.ye.movie.moviebackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false , length = 150)
    @NotBlank(message = "Need to add Title of Movie")
    private String title;

    @Column(nullable = false , length = 150)
    @NotBlank(message = "Need to add Director of Movie")
    private String director;

    @Column(nullable = false , length = 150)
    @NotBlank(message = "Need to add Name of the Studio")
    private String studio ;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false)
    @NotNull(message = "Need to add ReleaseYear")
    private int releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "Need to add Movie's poster")
    private String poster;
}
