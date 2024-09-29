package com.ye.movie.moviebackend.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Integer movieId;

    @NotBlank(message = "Need to add Title of Movie")
    private String title;


    @NotBlank(message = "Need to add Director of Movie")
    private String director;

    @NotBlank(message = "Need to add Name of the Studio")
    private String studio ;

    private Set<String> movieCast;

    @NotNull(message = "Need to add ReleaseYear")
    private int releaseYear;

    @NotBlank(message = "Need to add Movie's poster")
    private String poster;

    @NotBlank(message = "Need to add Poster's URL")
    private String posterURL;
}
