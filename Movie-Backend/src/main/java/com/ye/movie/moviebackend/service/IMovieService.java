package com.ye.movie.moviebackend.service;

import com.ye.movie.moviebackend.dto.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IMovieService {

        // crud query 

        MovieDto addMovie(MovieDto movieDto , MultipartFile file) throws IOException, RuntimeException;

        MovieDto getMovie(Integer movieId);

        List<MovieDto> getAllMovies();

        MovieDto updateMovie ( Integer movieId , MovieDto movieDto , MultipartFile file ) throws IOException ;

        String deleteMovie ( Integer movieId ) throws IOException;
}
